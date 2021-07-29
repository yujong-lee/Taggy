(ns taggy.components.tab
  (:require [re-frame.core :as rf]
            [re-com.core :refer [button horizontal-tabs]]
            
            [taggy.states.events :as events]
            [taggy.states.subs :as subs]))

(defn tab
  []
  (let [tabs                   @(rf/subscribe [::subs/all-types])
        {selected-tab-id :id}  @(rf/subscribe [::subs/current-type])]
    [:<>
     [horizontal-tabs
      :model      selected-tab-id
      :tabs       tabs
      :on-change  #(rf/dispatch [::events/update-current-type %1])]
     
     [button
      :label "Add"
      :on-click (fn []
                  (let [c       (-> tabs count inc str)
                        new-tab {:id (keyword c) :label c}
                        new-tabs (conj tabs new-tab)]
                    (rf/dispatch [::events/update-all-types new-tabs])))]]))
