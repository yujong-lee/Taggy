(ns taggy.components.header
  (:require [re-com.core :refer [v-box h-box gap title at]]))

(defn header []
  [v-box  :src (at)
   :children [[gap :size "30px"]
              [h-box :src (at)
               :children [[gap :size "40px"]
                          [title :src (at)
                           :level :level1
                           :label "Taggy"]]]]])
