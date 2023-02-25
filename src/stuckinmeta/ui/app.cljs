(ns stuckinmeta.ui.app
  (:require
    [reagent.core :as r]
    [stuckinmeta.ui.pages.landing :refer [landing-page-view]]))

(defn app-view []
  [landing-page-view])
