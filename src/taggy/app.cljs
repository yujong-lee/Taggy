(ns taggy.app
  (:require
   [re-frame.core :as rf]
   [taggy.components.input :as input]
   [taggy.components.items :refer [items]]
   [taggy.states.events :as events]
   [taggy.states.subs :as subs]))

(defn app
  []
  (let [type @(rf/subscribe [::subs/current-type])]
      [:<>
       [input/controls type]
       [items type]

       [:button
        {:on-click #(rf/dispatch [::events/update-current-type :abcd])}
        "abcd"]

       [:button
        {:on-click #(rf/dispatch [::events/update-current-type :item])}
        "item"]]))
