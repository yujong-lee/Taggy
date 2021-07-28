(ns taggy.states.db)

(def default-db
  {:next-id :5
   :current-type :item
   :all-tags {:item #{"one" "two" "three"}}
   :field {}
   :data {:item {:0 {:title "task0" :tags #{"one" "two"}}
                 :1 {:title "task1" :tags #{"two"}}
                 :2 {:title "task2" :tags #{"three"}}
                 :3 {:title "task3" :tags #{"one" "three"}}
                 :4 {:title "task4" :tags #{"one" "two"}}}}})