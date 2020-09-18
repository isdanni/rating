"""
Core API:

1. Add new layers;
2. generate system links;
3. handle requests;
"""
(ns crawler.server
  (:require [org.httpkit.server :refer :all]
            [ring.util.response :refer [file-response]]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.route :refer [resources not-found]]
            [compojure.handler :refer [site]]
            [compojure.core :refer [defroutes GET POST DELETE ANY context]]
            [clojure.java.io :as io]
            [net.cgrand.enlive-html :as enlive]
            [cemerick.austin.repls :refer (browser-connected-repl-js)]
            [crawler.core :as crawler]
            [clojure.core.async :as async]
            [clojure.data.json :as json]))

(enlive/deftemplate index
  (io/resource "public/index.html")
  []
  [:body] (enlive/append
            (enlive/html [:script (browser-connected-repl-js)])))

(defn ws-handler [request]
  (let [progress-chan (async/chan)]
    (with-channel request channel
      (on-close channel #(println "channel closed: " %))
      (on-receive channel (fn [domain]
                            (async/thread
                              (crawler/run domain progress-chan))))
      (async/go-loop []
                     (when-some [msg (async/<! progress-chan)]
                       (send! channel (json/write-str msg)))
                     (recur)))))

(defroutes all-routes
  (GET "/" req (index))
  (GET "/ws" [] ws-handler)
  (POST "/trigger" [domain]
        (async/thread (crawler/run domain))
        "Success")
  (resources "/")
  (not-found "<p>Page not found</p>"))

(defonce server (atom nil))

(defn app []
  (-> (site #'all-routes)
      wrap-reload
      (run-server {:port 3000})))

(defn start-server []
  (swap! server (fn [_] (app))))

(defn kill-server []
  (@server))

(comment (start-server)
         (kill-server))
