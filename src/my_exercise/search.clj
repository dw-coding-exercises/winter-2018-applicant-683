(ns my-exercise.search
  (:require [clojure.string :refer [lower-case]]
            [my-exercise.dw :as dw]))

(def prefix "ocd-division/country:us")

;; TODO: add county lookup
;; TODO: support zip codes
(defn gen-ocd-ids [params]
  "Generates OCD-IDs given form submission parameters."
  (let [{state :state place :city} params
        state (lower-case state)]
    (remove nil? [prefix
                  (str prefix "/state:" state)
                  (if (not (empty? place)) (str prefix "/state:" state "/place:" (lower-case place)))])))

(defn search [request]
  "Takes form submission parameters, generates OCD-IDs, and returns the result
   of querying the DW api for elections for those ids."
  (let [{params :params session :session} request
        ocd-ids (gen-ocd-ids params)]
    {:show-results? true
     :elections (dw/query-elections ocd-ids)}))

