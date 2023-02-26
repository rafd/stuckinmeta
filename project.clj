(defproject stuckinmeta "0.0.1"
  :dependencies [[org.clojure/clojure "1.11.0"]
                 [io.bloomventures/commons "0.13.2"]
                 [io.bloomventures/omni "0.30.0"]
                 [reagent "1.1.0"]
                 [garden "1.3.10"]
                 [cljsjs/react "17.0.2-0"]
                 [cljsjs/react-dom "17.0.2-0"]
                 [image-resizer "0.1.10"]
                 [markdown-clj "1.11.4"]]

  :main stuckinmeta.core

  :plugins [[io.bloomventures/omni "0.30.0"]]

  :omni-config stuckinmeta.omni-config/omni-config

  :profiles {:uberjar
             {:aot :all
              :prep-tasks [["omni" "compile"]
                           "compile"]}})
