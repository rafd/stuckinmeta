(ns stuckinmeta.omni-config
  (:require
   [stuckinmeta.config :refer [config]]))

(def omni-config
  {:omni/http-port (config :http-port)
   :omni/environment (config :environment)
   :omni/title "Stuck In Meta"
   :omni/cljs {:main "stuckinmeta.core"}
   :omni/css {:tailwind? true
              :tailwind-opts {:base-css-rules '[girouette.tw.preflight/preflight-v2_0_3]
                              :garden-fn 'girouette.tw.default-api/tw-v3-class-name->garden}}})
