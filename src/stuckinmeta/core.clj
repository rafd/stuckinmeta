(ns stuckinmeta.core
  (:gen-class)
  (:require
    [bloom.omni.core :as omni]
    [stuckinmeta.omni-config :refer [omni-config]]))

(defn start! []
  (omni/start! omni/system omni-config)
  nil)

(defn stop! []
  (omni/stop!))

(defn restart! []
  (stop!)
  (start!))

(defn -main []
  (start!))
