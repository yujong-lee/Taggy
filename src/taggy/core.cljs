(ns taggy.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as rf]
   [taggy.states.events :as events]
   [taggy.app :refer [app]]
   [taggy.config :as config]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [app] root-el)))

(defn init []
  (rf/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
