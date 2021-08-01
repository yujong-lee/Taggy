(ns taggy.components.content
  (:require [re-frame.core :as rf]
            [re-com.core :refer [v-box h-box gap at]]

            [taggy.states.subs :as subs]

            [taggy.components.tab :refer [tab]]
            [taggy.components.input :as input]
            [taggy.components.items :refer [items]]))

(defn- browse-area
  [type]
  [v-box :src (at)
   :size "5"
   :gap "30px"
   :children [[tab]
              [input/controls type]]])

(defn- result-area
  [type]
  [v-box :src (at)
   :size "7"
   :children [[gap :size "80px"]
              [items type]]])

(defn content
  ([]
   (let [{type :label} @(rf/subscribe [::subs/current-type])]
     [content type]))
  
  ([type]
   [h-box :src (at)
    :children [[gap :size "60px"]
               [browse-area type]
               [gap :size "1"]
               [result-area type]]]))
