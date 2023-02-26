(ns stuckinmeta.routes
  (:require
    [stuckinmeta.images :as images]
    [stuckinmeta.db :as db]))

(def api-routes
  (concat
    images/routes
    db/routes))

