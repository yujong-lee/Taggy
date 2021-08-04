(ns taggy.components.tab
  (:require [re-frame.core :as rf]
            [re-com.core :refer [horizontal-tabs]]

            [taggy.states.events :as events]
            [taggy.states.subs :as subs]))

(defn tab
  ([]
  (let [types          @(rf/subscribe [::subs/all-types])
        {:keys [id]}   @(rf/subscribe [::subs/current-type])
        handle-change  #(rf/dispatch [::events/select-tab %1])]
    [tab
     types id handle-change]))
  
  ([types id handle-change]
   (let [add-button             {:id :0 :label :+}
         tabs                   (conj types add-button)]
     [horizontal-tabs
      :model      id
      :tabs       tabs
      :on-change  handle-change])))
