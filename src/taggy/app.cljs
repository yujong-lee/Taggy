(ns taggy.app
  (:require
   [taggy.components.input :as input]
   [taggy.components.items :refer [items]]))

(defn app
  []
  [:<>
   [input/controls :item]
   [items :item]])
  