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
  [type id]
  (let [data @(rf/subscribe [::subs/datas type id])]
    [:<>
     [:h4 (:title data)]
     [tag-list (:tags data)]]))

(defn items
  [type]
  [:ul
   (let [ids @(rf/subscribe [::subs/filtered type])]
     (for [id ids]
       ^{:key id}
       [:li [item type id]]))])
