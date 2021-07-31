(ns taggy.states.subs
  (:require
   [re-frame.core :as rf]
   [clojure.set :as set]

   [taggy.macros :refer-macros [reg-sub-getter]]))

(reg-sub-getter ::field-ids [:field-ids])
(reg-sub-getter ::field-values-of-id [:field-values "id"])

(reg-sub-getter ::current-type [:current-type])

(reg-sub-getter ::all-types [:all-types])
(reg-sub-getter ::all-tags-of-type [:all-tags "type"])

(reg-sub-getter ::datas-of-type-id [:datas "type" "id"])

(reg-sub-getter ::datas-of-type [:datas "type"])

(reg-sub-getter ::field-values [:field-values])

(rf/reg-sub
 ::filtered-items
 (fn [[_ type]]
   [(rf/subscribe [::datas-of-type type])
    (rf/subscribe [::field-values])])

 (fn [[items fields] _]
   (let [ids
         (for [item items
               field-tags (vals fields)
               :let [item-tags (:tags (second item))
                     item-id (first item)]
               :when (and
                      (seq field-tags)
                      (empty? (set/difference field-tags item-tags)))]
           item-id)]
     (into #{} ids))))
