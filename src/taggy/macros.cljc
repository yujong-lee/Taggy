(ns taggy.macros)

;; Info: Only for taggy.states.subs/
;; Usage: (reg-sub-getter "datas" ["type" "id"])
;; Expand: (rf/reg-sub
;;          ::datas
;;          (fn [db [_ type id]]
;;            (get-in db [:datas type id])))

(defmacro reg-sub-getter
  [base sub-path]
  (let [_ (gensym)
        home "taggy.states.subs/"
        params (map gensym sub-path)
        full-path (-> base keyword (cons params) vec)]
    
    `(rf/reg-sub
      ~(keyword (str home base))
      (fn [db# 
           ~(->> params (cons _) (into []))]
        (get-in db# ~full-path)))))
