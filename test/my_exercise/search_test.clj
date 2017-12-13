(ns my-exercise.search-test
  (:require [my-exercise.search :refer :all]
            [clojure.test :refer :all]))

(deftest gen-ocd-ids-test
  (testing "passing in city and state generates expected ocd ids"
    (is (= ["ocd-division/country:us"
            "ocd-division/country:us/state:al"
            "ocd-division/country:us/state:al/place:birmingham"]
           (gen-ocd-ids {:state "AL" :city "Birmingham"})))))
