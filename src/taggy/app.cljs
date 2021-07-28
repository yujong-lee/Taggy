(ns taggy.app
  (:require
   [reagent.core :as ra]
   [taggy.components.input :as input]
   [taggy.components.items :refer [items]]))

(defn app
  []
  (let [type (ra/atom :item)]
    (fn []
      [:<>
       [input/controls @type]
       [items @type]
       [:button
        {:on-click #(reset! type :abcd)}
        "abcd"]
       
       [:button
        {:on-click #(reset! type :item)}
        "item"]])))
