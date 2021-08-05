(ns taggy.states.subs
  (:require
   [re-frame.core :as rf]
   [clojure.set :as set]
   [cljs.spec.alpha :as spec]
   [cljs.spec.test.alpha :as stest]

   [specs.common :as common]
   [taggy.macros :refer-macros [reg-sub-getter]]))

(reg-sub-getter ::field-ids [:field-ids])
(reg-sub-getter ::field-values-of-id [:field-values "id"])

(reg-sub-getter ::current-type [:current-type])
(reg-sub-getter ::all-types [:all-types])

(reg-sub-getter ::all-tags-of-type [:all-tags "type"])

(reg-sub-getter ::datas-of-type [:datas "type"])

(reg-sub-getter ::field-values [:field-values])

;; (spec/fdef filter-items
;;   :args (spec/cat
;;          :useful (spec/spec (spec/cat
;;                              :items (spec/map-of ::common/id ::common/item)
;;                              :fields (spec/map-of ::common/id ::common/tags)))
;;          :useless any?)
;;   :ret ((spec/coll-of ::common/items :kind vector?)))

(defn filter-items
  [[items fields] _]
  (let [result  (for [item items
                      field-tags (vals fields)
                      :let [item-tags (:tags (second item))]
                      :when (and
                             (seq field-tags)
                             (empty? (set/difference field-tags item-tags)))]
                  (merge {:id (first item)} (second item)))]
    (vec result)))

;; (stest/instrument `filter-items)

(rf/reg-sub
 ::filtered-items
 (fn [[_ type]]
   [(rf/subscribe [::datas-of-type type])
    (rf/subscribe [::field-values])])
 filter-items)
