(ns taggy.components.content
  (:require [re-frame.core :as rf]
            [re-com.core :refer [v-box h-box gap at]]

            [taggy.states.subs :as subs]

            [taggy.components.tab :refer [tab]]
            [taggy.components.input :as input]
            [taggy.components.table :refer [table]]))

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
   :size "6"
   :height "500px"
   :children [[table type]]])

(defn content
  ([]
   (let [{type :label} @(rf/subscribe [::subs/current-type])]
     [content type]))
  
  ([type]
   [h-box :src (at)
    :children [[gap :size "60px"]
               [browse-area type]
               [gap :size "60px"]
               [result-area type]
               [gap :size "60px"]]]))
