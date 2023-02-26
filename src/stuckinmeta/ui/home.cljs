(ns stuckinmeta.ui.home
  (:require
    [reagent.core :as r]
    [bloom.commons.pages :as pages]
    [stuckinmeta.images :as images]
    [stuckinmeta.ui.page :as page]
    [stuckinmeta.db :as db]))

(defn home-page-view [_page]
  (r/with-let [hunters (db/fetch [:many :hunter/id])
               missions (db/fetch [:many :mission/id])]
    [page/page-view {}
     [:div.home
      [:div
       [:h2 {:tw "font-heading"} "/missions"]
       [:div {:tw "flex gap-4 p-4"}
        (for [{:mission/keys [id codename]} @missions]
          ^{:key id}
          [:a.mission {:tw "text-theme-2 flex flex-col items-center font-text space-y-2"
                       :href (pages/path-for [:page/mission {:id (name id)}])}
           [:img {:src (images/mission-path id 240)
                  :tw "w-30 h-30"}]
           codename])]]
       [:div
        [:h2 {:tw "font-heading"} "/hunters"]
        [:div {:tw "flex gap-4 p-4"}
         (for [{:hunter/keys [id handle]} @hunters]
           ^{:key id}
           [:a.hunter {:tw "flex flex-col items-center text-theme-3 font-text space-y-2"
                       :href (pages/path-for [:page/hunter {:id (name id)}])}
            [:img {:src (images/hunter-path id 240)
                   :tw "w-30 h-30"}]
            [:div "@" handle]])]]]]))
