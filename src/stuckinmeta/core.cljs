(ns ^:figwheel-hooks
  stuckinmeta.core
  (:require
    [bloom.omni.reagent :as r]
    [bloom.commons.pages :as pages]
    [stuckinmeta.pages :refer [pages]]
    [stuckinmeta.ui.app :refer [app-view]]))

(enable-console-print!)

(defn render
  []
  (r/render [app-view]))

(defn ^:export init
  []
  (pages/initialize! pages)
  (render))

(defn ^:after-load reload
  []
  (render))
