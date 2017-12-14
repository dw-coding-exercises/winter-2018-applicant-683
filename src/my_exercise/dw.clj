(ns my-exercise.dw
  (:require [clojure.string :refer [join]]
            [clojure.edn :as edn]
            [clj-http.client :as client]))

;; TODO: make this configurable by an env var
(def dw-url "https://api.turbovote.org/elections/upcoming?district-divisions=")

(defn gen-url [ocd-ids]
  (str dw-url (join "," ocd-ids)))

;; TODO: handle errors omg
;; TODO: clean up the date formatting
(defn query-elections [ocd-ids]
  "Queries the Turbovote API for elections for a vector of OCD-IDs, returns edn"
  (edn/read-string (:body (client/get (gen-url ocd-ids)))))
