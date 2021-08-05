(ns taggy.components.tab
  (:require [re-frame.core :as rf]
            [re-com.core :refer [horizontal-tabs]]

            [taggy.states.events :as events]
            [taggy.states.subs :as subs]))

(defn tab
  ([]
  (let [types          @(rf/subscribe [::subs/all-types])
        {:keys [id]}   @(rf/subscribe [::subs/current-type])
        handle-change  #(rf/dispatch [::events/toggle-openness %1])]
    [tab
     types id handle-change]))
  
  ([types id handle-change]
     [horizontal-tabs
      :model      id
      :tabs       types
      :on-change  handle-change]))
