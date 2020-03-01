(ns film-ratings.handler.example
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response] 
            [clojure.java.io :as io]
            [integrant.core :as ig]))

(defmethod ig/init-key :film-ratings.handler/example [_ options]
  (fn [{[_] :ataraxy/result}]
    [::response/ok (io/resource "film_ratings/handler/example/example.html")]))
