(ns user)

(defn dev
  "Load and switch to the 'dev' namespace."
  []
  (require 'dev)
  (require 'space)
  (in-ns 'dev)
  :loaded)
