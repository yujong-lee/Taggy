(ns taggy.macros)

;; Info: simple getter shorthend
;; Usage: (reg-sub-getter "datas" ["type" "id"])
;; Expand: (rf/reg-sub
;;          ::datas
;;          (fn [db [_ type id]]
;;            (get-in db [:datas type id])))

(defmacro reg-sub-getter
  [base sub-path]
  (let [_ (gensym)
        params (map gensym sub-path)
        full-path (-> base keyword (cons params) vec)]
    
    `(rf/reg-sub
      ~(-> *ns* ns-name name (keyword base))
      (fn [db# 
           ~(->> params (cons _) (into []))]
        (get-in db# ~full-path)))))
