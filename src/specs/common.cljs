(ns specs.common
  (:require [cljs.spec.alpha :as spec]))

(spec/def ::id (spec/and
                      keyword?
                      #(-> %1 name js/parseInt nat-int?)))

(spec/def ::label string?)

(spec/def ::tags (spec/coll-of string? :kind set?))

(spec/def ::item (spec/keys :req-un [::label ::tags]))
