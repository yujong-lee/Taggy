(ns taggy.macros)

;; Info: simple getter shorthend
;; Usage: (reg-sub-getter ::name [:datas :item "id"])
;; Expand: (rf/reg-sub
;;          ::name
;;          (fn [db [_ id]]
;;            (get-in db [:datas :item id])))
(defmacro reg-sub-getter
  [id path]
  (let [_ (gensym)
        actual-path (mapv #(if (keyword? %1) %1 (gensym %1)) path)
        params (->> actual-path
                    (filter #(not (keyword? %1)))
                    (cons _)
                    (into []))]
    
    `(rf/reg-sub
      ~id
      (fn [db# ~params]
        (get-in db# ~actual-path)))))
