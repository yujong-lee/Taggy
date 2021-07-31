(ns taggy.components.input
  (:require ["@material-ui/core" :refer [TextField]]
            ["@material-ui/lab" :refer [Autocomplete]]

            [reagent.core :as ra]
            [re-frame.core :as rf]
            [re-com.core :refer [button v-box gap at]]

            [taggy.states.subs :as subs]
            [taggy.states.events :as events]))

(defn- control
  ([type id]
   (let [options        @(rf/subscribe [::subs/all-tags-of-type type])
         handle-change  #(rf/dispatch [::events/update-field id %2])]
     [control id options handle-change]))
  
  ([id options handle-change]
   [:> Autocomplete {:id (str "input id : " (name id))
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
   [v-box :src (at)
    :gap "20px"
    :children [(for [id ids]
                 ^{:key id}
                 [v-box :src (at)
                  :children [[gap :size "20px"]
                             [control type id]]])

               [button
                :src              (at)
                :label            "+"
                :on-click          handle-click
                :style {:height "30px"
                        :width "100%"}]]]))
