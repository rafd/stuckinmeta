(ns stuckinmeta.config
  (:require
   [bloom.commons.config :as config]))

(def config
  (config/read
    "config.edn"
    [:map
     [:http-port integer?]
     [:environment [:enum :prod :dev]]]))
