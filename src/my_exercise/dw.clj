(ns my-exercise.dw
  (:require [clojure.string :refer [join]]
            [clj-http.client :as client]))

;; TODO: make this configurable by an env var
(def dw-url "https://api.turbovote.org/elections/upcoming?district-divisions=")

(defn- gen-url [ocd-ids]
  (str dw-url (join "," ocd-ids)))

(defn query-elections [ocd-ids]
  (client/get (gen-url ocd-ids)))
