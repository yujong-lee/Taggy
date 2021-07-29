(ns taggy.components.input
  (:require [reagent.core :as ra]
            [re-frame.core :as rf]
            ["@material-ui/core" :refer [TextField]]
            ["@material-ui/lab" :refer [Autocomplete]]
            [taggy.states.subs :as subs]
            [taggy.states.events :as events]))

(defn- control
  [type id]
  [:> Autocomplete {:id (str "input id : " (name id))
                    :style {:width 600}
                    :multiple true
                    :free-solo true
                    :filter-selected-options true

                    :options @(rf/subscribe [::subs/all-tags type])
                    :on-change #(rf/dispatch [::events/update-field id %2])
                    :render-input (fn [^js params]
                                    (set! (.-variant params) "outlined")
                                    (set! (.-label params) "Select Tags")
                                    (ra/create-element TextField params))}])

(defn controls
  [type]
  (let [ids @(rf/subscribe [::subs/field-ids])]
    [:<>
     (for [id ids]
       ^{:key id} [control type id])

     [:button
      {:on-click #(rf/dispatch [::events/add-field])}
      "Add"]]))
