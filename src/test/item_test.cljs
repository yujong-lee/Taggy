(ns test.item-test
  (:require
   ["@testing-library/react" :as rtl]
   [cljs.test :refer [deftest is testing use-fixtures]]
   [taggy.components.items :as items]
   [reagent.core :as ra]))

(use-fixtures :each
  {:after rtl/cleanup})

(deftest tag-list
  (testing "it renders list of tags"
    (is
     (=
      "tag1"
      (-> (ra/as-element [items/tag-list ["tag1"]])
          (rtl/render)
          (.getByText "tag1")
          (.-innerHTML))))))
