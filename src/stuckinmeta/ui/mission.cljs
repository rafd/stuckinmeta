(ns stuckinmeta.ui.mission
  (:require
    [reagent.core :as r]
    [bloom.commons.pages :as pages]
    [stuckinmeta.images :as images]
    [stuckinmeta.ui.page :as page]
    [stuckinmeta.db :as db]))

(defn spoiler-view [opts node]
  (r/with-let [hide? (r/atom true)]
    (if @hide?
      [:div (merge opts {:title "Spoiler, click to view."
                         :on-click (fn [_] (reset! hide? false))})
       "?????"]
      [:div opts
       node])))

(defn hunter-view
  [hunter-id]
  (r/with-let [hunter (db/fetch [:one [:hunter/id hunter-id]])]
    (when @hunter
      ^{:key hunter-id}
      [:a.hunter {:tw "flex text-theme-3 font-text"
                  :href (pages/path-for [:page/hunter {:id (name (:hunter/id @hunter))}])}
       [:div "@" (:hunter/handle @hunter)]])))

(defn mission-page-view
  [[_ {:keys [id]}]]
  (r/with-let [mission-id (keyword "mission.id" id)
               mission (db/fetch [:one [:mission/id mission-id]])
               report (db/fetch [:report mission-id])]
    [page/page-view {}
     (when @mission
       [:div.mission {:tw "space-y-4"}
        [:div {:tw "flex flex-wrap gap-4 items-end"}
         [:img {:src (images/mission-path (:mission/id @mission) 400)
                :tw "w-50 aspect-square"}]

         [:div {:tw "space-y-1"}
          [:h2 {:tw "font-heading"} "/mission"]
          [:h1 {:tw "text-theme-2 font-heading leading-normal"
                :style {:font-size "2em"}} (:mission/codename @mission)]]]

        [:div {:tw "flex gap-8 flex-wrap"}
         [:div {:tw "space-y-2"}
          [:h2 {:tw "font-heading"} "/hunters"]
          [:div
           (for [hunter-id (:mission/hunters @mission)]
             ^{:key hunter-id}
             [hunter-view hunter-id])]]

         [:div {:tw "space-y-2"}
          [:h2 {:tw "font-heading"} "/verse"]
          [spoiler-view {:tw "text-white font-text"}
           (:mission/verse @mission)]]

         [:div {:tw "space-y-2"}
          [:h2 {:tw "font-heading"} "/monster"]
          [spoiler-view {:tw "text-white font-text"}
           (:mission/monster @mission)]]]

        (when @report
          [:div.report
           [:h2 {:tw "font-heading"} "/report"]
           [:div.content {:tw "whitespace-pre-wrap text-sm bg-black text-white p-4 font-long-text"}
            (:report/text @report)]])])]))

