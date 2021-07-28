(ns taggy.components.input
  (:require [reagent.core :as ra]
            [re-frame.core :as rf]
            ["@material-ui/core" :refer [TextField]]
            ["@material-ui/lab" :refer [Autocomplete]]
            [taggy.states.subs :as subs]
            [taggy.states.events :as events]))


(defn input-control
  [type]
  (let [id (gensym)]
    [:> Autocomplete {:id (str "input id : " id)
                      :style {:width 600}
                      :multiple true
                      :free-solo true
                      :filter-selected-options true

                      :options @(rf/subscribe [::subs/all-tags type])
                      :on-change #(rf/dispatch [::events/update-field type id %2])
                      :render-input (fn [^js params]
                                      (set! (.-variant params) "outlined")
                                      (set! (.-label params) "Select Tags")
                                      (ra/create-element TextField params))}]))
