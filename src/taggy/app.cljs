(ns taggy.app
  (:require
   [re-frame.core :as rf]
   [re-com.core :refer [gap v-box h-box at]]

   [taggy.states.subs :as subs]
   [taggy.components.input :as input]
   [taggy.components.header :refer [header]]
   [taggy.components.items :refer [items]]
   [taggy.components.tab :refer [tab]]))

(defn app
  []
  (let [{type :label} @(rf/subscribe [::subs/current-type])]
    [h-box :src (at)
     :gap "40px"
     :children [[gap :size "30px"]
                [v-box :src (at)
                 :size "10"
                 :gap "30px"
                 :children [[header]
                            [tab]
                            [input/controls type]]]
                [gap :size "1"]
                [v-box :src (at)
                 :size "11"
                 :children [[gap :size "100px"]
                            [items type]]]]]))
