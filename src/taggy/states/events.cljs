(ns taggy.states.events
  (:require
   [re-frame.core :as rf]
   [taggy.states.db :as db]))

(rf/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(rf/reg-event-db
 ::update-field
 (fn [db [_ id value]]
   (assoc-in db [:field-values id] (into #{} value))))

(rf/reg-event-db
 ::add-field
 (fn [db _]
   (update-in db [:field-ids]
              #(vec (conj %1 (-> %1 last inc))))))

(rf/reg-event-db
 ::toggle-openness
 (fn [db [_ id]]
   (let [label  ({0 :Public 1 :Private} id)]
       (assoc-in db [:current-type] {:id id :label label}))))
