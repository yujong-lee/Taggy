(ns taggy.components.input
  (:require ["@material-ui/core" :refer [TextField]]
            ["@material-ui/lab" :refer [Autocomplete]]

            [reagent.core :as ra]
            [re-frame.core :as rf]

            [taggy.states.subs :as subs]
            [taggy.states.events :as events]))

(defn- control
  ([type id]
   (let [options        @(rf/subscribe [::subs/all-tags type])
         handle-change  #(rf/dispatch [::events/update-field id %2])]
     [control id options handle-change]))
  
  ([id options handle-change]
   [:> Autocomplete {:id (str "input id : " (name id))
                     :style {:width 600}
                     :multiple true
                     :free-solo true
                     :filter-selected-options true
                     :options options
                     :on-change handle-change
                     :render-input (fn [^js params]
                                     (set! (.-variant params) "outlined")
                                     (set! (.-label params) "Select Tags")
                                     (ra/create-element TextField params))}]))

(defn controls
  ([type]
   (let [ids           @(rf/subscribe [::subs/field-ids])
         handle-click  #(rf/dispatch [::events/add-field])]
     [controls type ids handle-click]))

  ([type ids handle-click]
   [:<>
    (for [id ids]
      ^{:key id} [control type id])

    [:button {:on-click handle-click} "Add"]]))
