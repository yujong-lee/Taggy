(ns taggy.components.header
  (:require [re-com.core :refer [title at]]))

(defn header []
  [title :src (at)
   :level :level1
   :label "Taggy"])
