(ns stuckinmeta.images
  (:require
    #?@(:clj
         [[clojure.java.io :as io]
          [clojure.string :as string]
          [image-resizer.core :as resizer]
          [image-resizer.format :as resizer.format]
          [ring.util.response :as ring.response]]
         :cljs
         [[clojure.string :as string]])))

#?(:clj
   (do
     (def image-url-path "images/")

     (def image-file-path "./db/")

     (defn resize [f size]
       (resizer.format/as-stream (resizer/resize f size size) "jpg"))

     #_(resize (io/file "db/missions/sand-and-stone/cover.png") 100)
     #_(with-open [out (io/output-stream (io/file "test.jpeg"))]
         (io/copy (resize (io/file "db/missions/sand-and-stone/image-map.png") 100)
                  out))

     (defn image-response
       [path size]
       (let [path (str image-file-path path)
             f (->> [path
                     (string/replace path "jpeg" "png")
                     (string/replace path "jpeg" "jpg")]
                    (map io/file)
                    (filter (fn [^java.io.File f] (.exists f)))
                    first)]
         (some-> f
                 (resize size)
                 ring.response/response
                 (ring.response/content-type "image/jpeg"))))

     (def routes
       [[[:get "/imgs/*"]
         (fn [request]
           (image-response (get-in request [:params :*])
                           (Integer. ^java.lang.String (get-in request [:query-params "size"]))))]])))

(defn hunter-path [id size]
  (str "/imgs/hunters/" (name id) ".jpeg?size=" size))

(defn mission-path [mission-id image-name size]
  (str "/imgs/missions/" (name mission-id) "/" image-name ".jpeg?size=" size))
