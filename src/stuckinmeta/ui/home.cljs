(ns stuckinmeta.ui.home
  (:require
    [bloom.commons.pages :as pages]))

(defn home-page-view [_page]
  [:div "HOME"
   [:h2 "Hunters"]
   [:a {:href (pages/path-for [:page/hunter {:id "norton"}])} "Norton"]

   ])
