(ns taggy.states.events
  (:require
   [re-frame.core :as rf]
   [taggy.states.db :as db]))

(defn inc-id
  [id]
  (->
   id
   name
   js/parseInt
   inc
   str
   keyword))

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
              #(vec (conj %1 (-> %1 last inc-id))))))

(rf/reg-event-db
 ::update-data
 (fn [db [_ type id data]]
   (assoc-in db [:data type id] data)))

(rf/reg-event-db
 ::add-data
 (fn [db [_ type title additional]]
   (let [id (:next-id db)
         tags (->> (type db)
                   vals
                   (apply concat)
                   (into #{}))
         data (merge additional {:title title :tags tags})]
     (assoc-in db [:datas type]  data)
     (assoc-in db [:next-id id] (inc-id id)))))
