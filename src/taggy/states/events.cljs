(ns taggy.states.events
  (:require
   [re-frame.core :as rf]
   [taggy.states.db :as db]))

(defn inc-id
  [keyword]
  (->
   keyword
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
 (fn [db [_ type id value]]
   (assoc-in db [:field type id] (into #{} value))))

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
     (assoc-in db [:data type]  data)
     (assoc-in db [:next-id id] (inc-id id)))))

(rf/reg-event-db
 ::update-type
 (fn [db [_ type]]
   (assoc-in db [:current-type] type)))
