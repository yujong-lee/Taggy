(ns taggy.app
  (:require
   [taggy.components.input :refer [input-control]]
   [taggy.components.items :refer [items]]))

(defn app
  []
  [:<>
   [input-control :item]
   [input-control :item]
   [input-control :item]
   [items :item]])
  