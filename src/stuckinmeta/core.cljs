(ns ^:figwheel-hooks
  stuckinmeta.core
  (:require
    [bloom.omni.reagent :as r]
    [stuckinmeta.ui.app :refer [app-view]]))

(enable-console-print!)

(defn render
  []
  (r/render [app-view]))

(defn ^:export init
  []
  (render))

(defn ^:after-load reload
  []
  (render))
