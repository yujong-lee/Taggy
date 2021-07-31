(ns taggy.states.subs
  (:require
   [re-frame.core :as rf]
   [clojure.set :as set]

   [taggy.macros :refer-macros [reg-sub-getter]]))

(reg-sub-getter "field-ids" [])
(reg-sub-getter "field-values" ["id"])

(reg-sub-getter "current-type" [])

(reg-sub-getter "all-types" [])
(reg-sub-getter "all-tags" ["type"])

(reg-sub-getter "datas" ["type" "id"])

(rf/reg-sub
 ::datas-of-type
 (fn [db [_ type]]
   (get-in db [:datas type])))

(rf/reg-sub
 ::all-field-values
 (fn [db _]
   (get-in db [:field-values])))

(rf/reg-sub
 ::filtered
 (fn [[_ type]]
   [(rf/subscribe [::datas-of-type type])
    (rf/subscribe [::all-field-values])])

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