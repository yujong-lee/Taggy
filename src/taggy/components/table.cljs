(ns taggy.components.table
  (:require ["@material-ui/data-grid" :refer [DataGrid]]

            [clojure.string :as string]
            [re-frame.core :as rf]
            
            [taggy.states.subs :as subs]))

(defn- tag-renderer [tags]
  (string/join ", " tags))

(def cols [{:field "label"
            :headerName "Label"
            :width 150}
           {:field "tags"
            :headerName "Tags"
            :width 250
            :renderCell #(tag-renderer (.-value %1))}])

(defn table [type]
  [:> DataGrid {:rows    @(rf/subscribe [::subs/filtered-items type])
                :columns cols}])
