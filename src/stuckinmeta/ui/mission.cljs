(ns stuckinmeta.ui.mission
  (:require
    [reagent.core :as r]
    [garden.core :as garden]
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
        [:img {:src (images/mission-path (:mission/id @mission) "cover" 400)
                :tw "w-100 max-w-full aspect-square"}]

        [:div {:tw "space-y-1"}
         [:h2 {:tw "font-heading"} "/mission"]
         [:h1 {:tw "text-theme-2 font-heading leading-normal"
               :style {:font-size "2em"}} (:mission/codename @mission)]]

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
          [:<>
           [:style
            (garden/css
              [:#report

               [:h2
                {:font-weight "bold"
                 :margin-top "1em"}]

               [:ul
                {:list-style "disc"
                 :margin-left "2em"}]

               [:img
                {:margin "1em"}]])]
           [:div#report
            [:h2 {:tw "font-heading"} "/report"]

            [:div.content
             {:tw "text-sm text-white font-long-text"
              :dangerouslySetInnerHTML {:__html (:report/text @report)}}]]])])]))

