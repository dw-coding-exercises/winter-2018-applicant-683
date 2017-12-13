(ns my-exercise.search
  (:require [clojure.string :refer [lower-case]]
            [my-exercise.dw :as dw]))

(def prefix "ocd-division/country:us")

;; TODO: add county lookup
(defn gen-ocd-ids [params]
  "Generates OCD-IDs given form submission parameters."
  (let [{state :state place :city} params
        state (lower-case state)]
    [prefix
     (str prefix "/state:" state)
     (str prefix "/state:" state "/place:" (lower-case place))]))

(defn search [{params :params}]
  "Takes form submission parameters, generates OCD-IDs, and returns the result
   of querying the DW api for elections for those ids."
  (let [ocd-ids (gen-ocd-ids params)]
    (pr-str params ocd-ids (dw/query-elections ocd-ids))))

