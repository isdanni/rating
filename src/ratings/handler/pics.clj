(ns ratings.handler.pics
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response] 
            [clojure.java.io :as io]
            [integrant.core :as ig]))

(defmethod ig/init-key :ratings.handler/example [_ options]
  (fn [{[_] :ataraxy/result}]
    [::response/ok (io/resource "ratings/handler/pics/dashboard.html")]))