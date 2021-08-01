(ns taggy.app
  (:require
   [re-com.core :refer [v-box at]]

   [taggy.components.header :refer [header]]
   [taggy.components.content :refer [content]]))

(defn app
  []
  [v-box :src (at)
   :gap "30px"
   :children [[header]
              [content]]])
