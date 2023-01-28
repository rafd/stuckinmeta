(ns stuckinmeta.ui.app)

(defn app-view []
  [:<>
   [:style "@import url('https://fonts.googleapis.com/css2?family=Press+Start+2P&family=VT323&display=swap');
           p { font-family: 'VT323', monospace; font-size: 2em}
           button, h1 { font-family: 'Press Start 2P', monospace;}
           "]
   [:section.hero {:tw "bg-#001836 flex w-screen items-start min-h-screen"}
    [:img {:src "/images/hero.png"
           :tw "max-h-100vh grow"}]
    [:div {:tw "p-12 space-y-12 w-60em"}
     [:h1 {:tw "text-#00fffe font-size-4em"} "stuck" [:br] "_in" [:br] "//meta"]
     [:section {:tw "text-#fda558 space-y-6"}
      [:p
       "Godzilla is rampaging through Middle Earth?" [:br] "The Xenomorph has found its way onto the Battlestar Galactica?"]
      [:p {:tw "ml-12"}
       "A corruption is spreading through the metaverse, and someone needs to fix it. Will it be you?"]

      [:p {:tw "ml-24"}
       "No, not you! That's ridiculous. The Overmind is on it. " [:br] "...but, until it figures out what's going on, we need someone to fix those rogue servers. One by one. Might as well be you."]]
     [:section {:tw "text-#fc40da space-y-6"}
      [:p "Stuck in Meta is a massive multigroup ttRPG, powered by Monster of the Week. DnD meets Westworld meets the Metaverse, in a continously evolving open universe of shared adventures."]
      [:p "Build a character. Join a one-off session (or run your own)." [:br] "Make some friends. Have some laughs. Slay some monsters."]
      [:p {:tw "text-right"} "...because the metaverse needs you."]]
     [:button {:tw "border border-4 border-#00fffe text-#00fffe text-4xl p-4"} "> Jack In"]]]
   #_[:section.setting
    [:p "In the near future, most humans have uploaded themselves to the metaverse, living out their wildest dreams amongst their favorite franchises. But, on some servers, rogue monsters are finding their way into the wrong universes and terrorizing the wrong AI NPCs and players. Players are unhappy. Profits are down! While the Overmind is figuring out the root cause, it's up to you and your team to patch the corrupted servers."]]])
