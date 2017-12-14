(ns my-exercise.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.util.response :refer [redirect]]
            [my-exercise.home :as home]
            [my-exercise.search :refer [search]]))

(defroutes app
  (GET "/" [] home/page)
  ;; TODO: redirect the url back to the homepage so you're not stuck on /search
  (POST "/search" request
        (let [results (search request)]
          (home/page (merge request results))))
  (route/resources "/")
  (route/not-found "Not found"))

(def handler
  (-> app
      (wrap-defaults site-defaults)
      wrap-reload))
