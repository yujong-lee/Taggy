(ns taggy.components.items
  (:require [re-frame.core :as rf]
            [re-com.core :refer [title label at]]

            
            [taggy.states.subs :as subs]))

(defn- tag-list
  [list]
  [:<>
   (for [element list]
     ^{:key (gensym element)}
     [label :src (at) :label element])])

(defn- item
  ([type id]
     (item @(rf/subscribe [::subs/datas-of-type-id type id])))

  ([data]
   [:<>
    [title :src (at) :label (:title data) :level :level3]
    [tag-list (:tags data)]]))

(defn items
  ([type]
   (items type @(rf/subscribe [::subs/filtered-ids type])))

  ([type ids]
   [:ul
    (for [id ids]
      ^{:key id}
      [:li [item type id]])]))
