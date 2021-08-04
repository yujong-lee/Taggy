(ns specs.common
  (:require [cljs.spec.alpha :as spec]))

(spec/def :common/id (spec/and
                      keyword?
                      #(-> %1 name js/parseInt nat-int?)))

(spec/def :common/label string?)

(spec/def :common/tags (spec/coll-of string? :kind set?))

(spec/def :common/item (spec/keys :req-un [:common/label :common/tags]))

(spec/def :common/field (spec/keys :req-un [:common/label :common/tags]))
