(ns taggy.app
  (:require
   [re-frame.core :as rf]
   [taggy.components.input :as input]
   [taggy.components.items :refer [items]]
   [taggy.states.subs :as subs]
   [taggy.components.tab :refer [tab]]))

(defn app
  []
  (let [{type :label} @(rf/subscribe [::subs/current-type])]
      [:<>
       [tab]
       [input/controls type]
       [items type]]))
