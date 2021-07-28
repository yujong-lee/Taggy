(ns taggy.states.subs
  (:require
   [re-frame.core :as rf]
   [clojure.set :as set]))

(rf/reg-sub
 ::item
 (fn [db [_ type id]]
   (-> db
       :data
       type
       id)))

(rf/reg-sub
 ::filtered
 (fn [db [_ type]]
   (let [ids (for [item (-> db :data type)
                   field-tags (-> db :field type vals)
                   :let [item-tags (:tags (second item))
                         item-id (first item)]
                   :when (and (seq field-tags)
                              (empty? (set/difference field-tags item-tags)))]
               item-id)]
     (into #{} ids))))

(rf/reg-sub
 ::field
 (fn [db [_ type id]]
   (-> db
       :field
       type
       id)))

(rf/reg-sub
 ::all-tags
 (fn [db [_ type]]
   (-> db
       :all-tags
       type)))
