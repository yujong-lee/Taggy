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
 ::update-field ;; Todo : better name like I did "select-tab"?
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
 ::select-tab
 (fn [db [_ new-type-id]]
   (let [types           (:all-types db)
         new-type-label  (if (= new-type-id :0)
                           :+ ;; Todo
                           (-> #(= new-type-id (:id %1))
                               (filter types)
                               first
                               :label))]
     (if (= new-type-label :+)
       (update-in db [:all-types] #(conj %1 {:id :new :label :new})) ;; Todo
       (assoc-in db [:current-type] {:id new-type-id
                                     :label new-type-label})))))

(rf/reg-event-db
 ::update-all-types
 (fn [db [_ types]]
   (assoc-in db [:all-types] types)))
