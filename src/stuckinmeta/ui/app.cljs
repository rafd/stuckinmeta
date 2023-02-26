(ns stuckinmeta.ui.app
  (:require
    [bloom.commons.pages :as pages]))

(defn app-view []
  [:<>
   [:style
    "@import url('https://fonts.googleapis.com/css2?family=Press+Start+2P&family=VT323&display=swap');"
    ".font-heading { font-family: 'Press Start 2P', monospace; }"
    ".font-text { font-family: 'VT323', monospace; font-variant-ligatures: none; font-size: 1.5em; }"
    ".font-long-text { font-family: 'Courier New', monospace; }"
    ".bg-theme { background-color:#051633; }"
    ".text-theme-1 { color:#00fffe; }"
    ".text-theme-2 { color:#fda558; }"
    ".text-theme-3 { color:#fc40da; }"]
   [pages/current-page-view]])
