(ns taggy.components.tab
  (:require [reagent.core :as ra]
            [re-frame.core :as rf]
            [taggy.states.events :as events]
            [re-com.core :refer [button horizontal-tabs]]))

(defn tab
  []
  (let [tab-defs        (ra/atom [{:id ::1 :label :item}
                                  {:id ::2 :label :abcd}])
        selected-tab-id (ra/atom (:id (first @tab-defs)))]
    (fn []
      [:<>
       [horizontal-tabs
        :model     selected-tab-id
        :tabs      tab-defs
        :on-change (fn [new-id]
                     (rf/dispatch [::events/update-current-type
                                   (-> #(= new-id (:id %))
                                       (filter @tab-defs)
                                       first
                                       :label)])
                     (reset! selected-tab-id new-id))]
       [button
        :label "Add"
        :on-click (fn []
                    (let [c       (str (inc (count @tab-defs)))
                          new-tab {:id (keyword c) :label c}]
                      (swap! tab-defs conj new-tab)))]])))

