(ns specs.common
  (:require [cljs.spec.alpha :as spec]))

(spec/def ::id nat-int?)

(spec/def ::label string?)

(spec/def ::tags (spec/coll-of string? :kind set?))

(spec/def ::item (spec/keys :req-un [::label ::tags]))

(spec/def ::items (spec/coll-of
                   (spec/keys :req-un [::id ::label ::tags])
                   :kind vector?))
