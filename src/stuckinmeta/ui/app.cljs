(ns stuckinmeta.ui.app
  (:require
    [reagent.core :as r]))

(def images
  [{:image/path "/images/hero1.png"
    :image/colors ["#051633" "#00fffe" "#fda558" "#fc40da"]}
   {:image/path "/images/hero2.png"
    :image/colors ["#050505"  "#ff04fd" "#fefffa" "#0deafb"]}
   {:image/path "/images/hero3.png"
    :image/colors ["#050302" "#f941e5" "#fdffff" "#6497ff"]}
   {:image/path "/images/hero4.png"
    :image/colors ["#15201f" "#fe01e9" "#fe01e9" "#fe01e9"]}
   {:image/path "/images/hero5.png"
    :image/colors ["#080819" "#fb04c3" "#c6a5e9" "#2c7ed6"]}])

(defn app-view []
  (r/with-let [image (r/atom (first images))
               timeout (r/atom nil)
               swap-image! (fn swap-image! []
                             (js/clearTimeout @timeout)
                             (reset! timeout (js/setTimeout swap-image! 15000))
                             (reset! image (rand-nth (vec (disj (set images) @image)))))
               _ (reset! timeout (js/setTimeout swap-image! 15000))]
    (let [[color-1 color-2 color-3 color-4] (:image/colors @image)]
      [:div {:style {:background-color color-1
                     :transition "background-color linear 2s"}}
       [:style "@import url('https://fonts.googleapis.com/css2?family=Press+Start+2P&family=VT323&display=swap');
               p { font-family: 'VT323', monospace; font-size: 2em; font-variant-ligatures: none;}
               p {line-height: 1.1}
               button, h1 { font-family: 'Press Start 2P', monospace;}
               .bg { background-position: 22.5% bottom}
               @media (min-width: 1024px) {
                 .bg { background-position: left bottom}
               }
               "]
       [:section.hero

        [:div.bg {:tw ["w-screen h-100vh lg:h-screen"
                       "fixed top-0"
                       "bg-no-repeat bg-cover lg:bg-contain lg:bg-bottom"]
                  #_(Math/round (* 100 (/ 1606 1024)))
                  :style {:background-image (str "url(" (:image/path @image) ")")
                          :background-color color-1
                          :transition "background-color linear 2s, background-image linear 2s"}
                  :on-click (fn [_]
                              (swap-image!))}]
        [:div.content {:tw ["md:p-12 p-6 space-y-12 max-w-58em ml-auto z-10 relative mt-420px md:mt-0"]}
         [:h1 {:tw "font-size-3em "
               :style {:background color-1
                       :padding "0.5em"
                       :display "inline-block"
                       :color color-2
                       :transition "background linear 2s, color linear 2s"}}
          "stuck" [:br]
          [:span {:style {:color color-3
                          :transition "color linear 2s"}} "_"]
          "in" [:br]
          [:span {:style {:color color-4
                          :transition "color linear 2s"}} "./"]
          "meta"]
         [:section {:tw "space-y-6 text-sm"
                    :style {:color color-3
                            :transition "color linear 2s"}}
          [:p {:tw "p-2 inline-block"
               :style {:background color-1
                       :transition "background linear 2s"}}
           "Godzilla is rampaging across Middle Earth!" [:br] "The Xenomorph has found its way onto the Battlestar Galactica?!"]
          [:p {:tw "md:ml-12 p-2 inline-block"
               :style {:background color-1
                       :transition "background linear 2s"}}
           "A corruption is spreading through the metaverse, and " [:br] "someone needs to fix it! Will it be you?"]
          [:p {:tw "md:ml-24 p-2 inline-block"
               :style {:background color-1
                       :transition "background linear 2s"}}
           "No, not you! That's ridiculous. The Overmind will handle it. After all, it runs this place." " ...but, until it figures things out, we need someone to patch those rogue servers. One by one. Might as well be you."]]

         [:section {:tw "space-y-6 text-sm"
                    :style {:color color-4
                            :transition "color linear 2s"}}
          [:p {:tw "p-2 inline-block"
               :style {:background color-1
                       :transition "background linear 2s"}}
           "Stuck in Meta is a massive multigroup ttRPG campaign, powered by Monster of the Week. It's DnD meets Westworld meets the Metaverse, in a continously evolving open universe of parallel adventures."]
          [:p {:tw "md:ml-12 p-2 inline-block"
               :style {:background color-1
                       :transition "background linear 2s"}}
           "Build a character. Join a one-off session (or run your own)." [:br] "Make some friends. Have some laughs. Slay some monsters."]
          [:p {:tw "md:ml-24 text-right p-2 inline-block"
               :style {:background color-1
                       :transition "background linear 2s"}}
           "...because the metaverse needs you."]]
         [:button {:tw "ml-36 border border-4 p-4 whitespace-nowrap"
                   :style {:color color-2
                           :background color-1
                           :border-color color-2
                           :transition "color linear 2s, border-color linear 2s"}}
          "> Jack In"]]]
       #_[:section.setting
          [:p "In the near future, most humans have uploaded themselves to the metaverse, living out their wildest dreams amongst their favorite franchises. But, on some servers, rogue monsters are finding their way into the wrong universes and terrorizing the wrong AI NPCs and players. Players are unhappy. Profits are down! While the Overmind is figuring out the root cause, it's up to you and your team to patch the corrupted servers."]]])))

;; TODO
;; fix hero 1
;; make sure all images are same width
