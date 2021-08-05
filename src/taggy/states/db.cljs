(ns taggy.states.db)

(def default-db
  {:next-id :10
   :field-ids [:0]
   :field-values {}

   :current-type {:id :1 :label :abcd} 

   :all-types [{:id :1 :label :abcd}
               {:id :2 :label :efgh}]
   :all-tags {:abcd #{"one" "two" "three"}
              :efgh #{"A" "B" "C"}}

   :datas {:abcd {:0 {:label "task0" :tags #{"one" "two"}}
                  :1 {:label "task1" :tags #{"two"}}
                  :2 {:label "task2" :tags #{"three"}}
                  :3 {:label "task3" :tags #{"one" "three"}}
                  :4 {:label "task4" :tags #{"one" "two"}}}
           :efgh {:5 {:label "aaaaa" :tags #{"A" "B"}}
                  :6 {:label "bbbbb" :tags #{"A"}}
                  :7 {:label "ccccc" :tags #{"B"}}
                  :8 {:label "ddddd" :tags #{"B" "C"}}
                  :9 {:label "eeeee" :tags #{"C" "A"}}}}})

;; TODO

;; 테스트 코드 다 짜야 뭔가 수정할 수 있을 듯
;; type은 키워드, tab은 id 포함한 맵이라고 정해야할 듯
;; 그래야 혼란 없고 책임문제가 깔끔함
;; id도 여러개 있오서 햇갈릴 수 있음
;; 이벤트에서 db참조? 이거 sub에서 한거처럼 다르게 하는거 없나 여기서 2형 sub를 받는다던가
;; gap도 속성으로 들어가는거 말고 중간중간 children으로 들어가는거 최대한 걷어내야함
;; 사이 공백 같은건 container로 따로 만들고, browse area, result area 등 이런 의미에 맞게 구성해주길 바람.


 