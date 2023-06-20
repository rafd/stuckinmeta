(ns stuckinmeta.ui.hunter
  (:require
    [bloom.commons.pages :as pages]
    [reagent.core :as r]
    [stuckinmeta.ui.page :as page]
    [stuckinmeta.images :as images]
    [stuckinmeta.db :as db]))

(defn hunter-page-view [[_ {:keys [id]}]]
  (r/with-let [hunter-id (keyword "hunter.id" id)
               hunter (db/fetch [:one [:hunter/id hunter-id]])
               missions (db/fetch [:search {:mission/hunters #{hunter-id}}])]
    [page/page-view {}
     (when @hunter
       [:div.hunter {:tw "space-y-4"}
        [:img {:src (images/hunter-path (:hunter/id @hunter) 400)}]

        [:div {:tw "space-y-2"}
         [:h2 {:tw "font-heading"} "/hunter"]
         [:h1 {:tw "text-theme-3 font-text"} "@" (:hunter/handle @hunter)]]

        (when @missions
          [:div {:tw "space-y-2"}
           [:h2 {:tw "font-heading"} "/missions"]
           (for [mission (->> @missions
                              (sort-by (fn [m] (first (:mission/dates m)))))]
             ^{:key (:mission/id mission)}
             [:a {:tw "block text-theme-2 font-text"
                  :href (pages/path-for [:page/mission {:id (name (:mission/id mission))}])}
              (:mission/codename mission)])])])]))
