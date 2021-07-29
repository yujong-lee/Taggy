(ns taggy.states.subs
  (:require
   [re-frame.core :as rf]
   [clojure.set :as set]

   [taggy.macros :refer-macros [reg-sub-getter]]))

(reg-sub-getter "datas" ["type" "id"])

(reg-sub-getter "all-tags" ["type"])

(reg-sub-getter "current-type" [])

(reg-sub-getter "field-ids" [])
(reg-sub-getter "field-values" ["id"])

(rf/reg-sub
 ::filtered
 (fn [db [_ type]]
   (let [ids (for [item (-> db :datas type)
                   field-tags (-> db :field-values vals)
                   :let [item-tags (:tags (second item))
                         item-id (first item)]
                   :when (and (seq field-tags)
                              (empty? (set/difference field-tags item-tags)))]
               item-id)]
     (into #{} ids))))
