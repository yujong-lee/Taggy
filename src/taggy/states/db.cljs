(ns taggy.states.db)

(def default-db
  {:next-id :10
   :all-tags {:item #{"one" "two" "three"}
              :abcd #{"A" "B" "C"}}
   :field {}
   :data {:item {:0 {:title "task0" :tags #{"one" "two"}}
                 :1 {:title "task1" :tags #{"two"}}
                 :2 {:title "task2" :tags #{"three"}}
                 :3 {:title "task3" :tags #{"one" "three"}}
                 :4 {:title "task4" :tags #{"one" "two"}}}
          :abcd {:5 {:title "aaaaa" :tags #{"A" "B"}}
                 :6 {:title "bbbbb" :tags #{"A"}}
                 :7 {:title "ccccc" :tags #{"B"}}
                 :8 {:title "ddddd" :tags #{"B" "C"}}
                 :9 {:title "eeeee" :tags #{"C" "A"}}}}})