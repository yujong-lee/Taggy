(ns taggy.components.items
  (:require [re-frame.core :as rf]
            
            [taggy.states.subs :as subs]))

(defn- tag-list
  [list]
  [:<>
   (for [element list]
     ^{:key (gensym element)}
     [:div element])])

(defn- item
  ([type id]
     (item @(rf/subscribe [::subs/datas-of-type-id type id])))

  ([data]
   [:<>
    [:h4 (:title data)]
    [tag-list (:tags data)]]))

(defn items
  ([type]
   (items type @(rf/subscribe [::subs/filtered-items type])))

  ([type ids]
   [:ul
    (for [id ids]
      ^{:key id}
      [:li [item type id]])]))
