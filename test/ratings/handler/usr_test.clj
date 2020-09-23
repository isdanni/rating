(ns ratings.handler.usr-test
  (:require [clojure.test :refer :all]
            [integrant.core :as ig]
            [ring.mock.request :as mock]
            [ratings.handler.example :as example]))

(deftest smoke-test
  (testing "usr exists"
    (let [handler  (ig/init-key :ratings.handler/usr {})
          response (handler (mock/request :get "/usr"))]
      (is (= :ataraxy.response/ok (first response)) "response ok"))))

(deftest smoke-test
  (testing "not available"
    (let [handler  (ig/init-key :ratings.handler/post {})
          response (handler (mock/request :get "/post"))]
      (is (= :ataraxy.response/ok (first response)) "response ok"))))
