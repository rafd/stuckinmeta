(ns stuckinmeta.ui.page
  (:require
    [bloom.commons.pages :as pages]))

(defn logo-view []
  [:h1 {:tw "text-theme-1 font-heading text-3xl p-4 inline-block"}
   "stuck" [:br]
   [:span {:tw "text-theme-2"} "_"]
   "in" [:br]
   [:span {:tw "text-theme-3"} "./"]
   "meta"])

(defn menu-view []
  [:div
   [:a {:href (pages/path-for [:page/home {}])}
    [logo-view]]
   #_[:ul
    (for [{:keys [label path-args]}
          [{:label "Home"
            :path-args [:page/home {}]}]]
      ^{:key label}
      [:li
       [:a {:href (pages/path-for path-args)}
        label]])]])

(defn page-view [opts content]
  [:div.page {:tw "bg-theme text-theme-1 min-h-full"}
   [menu-view]
   [:div.content {:tw "p-4"}
    content]])
