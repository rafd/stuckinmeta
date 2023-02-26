(ns stuckinmeta.db
  (:require
    #?@(:clj
         [[clojure.set :as set]
          [clojure.string :as string]
          [clojure.java.io :as io]
          [bloom.commons.file-db :as fdb]
          [markdown.core :as markdown]
          [markdown.transformers]
          [stuckinmeta.images :as images]]
         :cljs
         [[bloom.commons.ajax :as ajax]
          [reagent.core :as r]])))

#?(:clj
   (do
     (def db-config
       {:data-path "db"})

     (def data
       (delay (fdb/all db-config)))

     (defn fetch
       [[query-id params]]
       (case query-id
         :many
         (let [k params]
           (->> @data
                (filter k)))
         :one
         (let [[k v] params]
           (->> @data
                (filter (fn [e]
                          (= (get e k) v)))
                first))

         :search
         (->> @data
              (filter (fn [e]
                        (->> params
                             (map (fn [[k v]]
                                    (cond
                                      (and (set? v) (set? (e k)))
                                      (boolean (seq (set/intersection v (e k))))
                                      :else
                                      (= v (e k)))))
                             (every? true?)))))

         :report
         (let [id params]
           (some-> (io/file (:data-path db-config) "missions" (name id) "report.md")
                   slurp
                   (markdown/md-to-html-string
                     :replacement-transformers
                     (into [(fn prepend-image-paths
                              [text state]
                              [(string/replace text #"(!\[.*?\]\()(.+?)(\))"
                                               (fn [[_ pre url post]]
                                                 (str pre
                                                      (images/mission-path id url 400)
                                                      post))) state])]
                           markdown.transformers/transformer-vector))
                   (->> (assoc {} :report/text))))))

     #_(fetch [:one [:hunter/id :hunter.id/zerxez]])
     #_(fetch [:many :hunter/id])
     #_(fetch [:report "sand-and-stone"])

     (def routes
       [[[:post "/api/query"]
         (fn [request]
           {:status 200
            :body (fetch (:query (:body-params request)))})]]))

   :cljs
   (do
     (defn fetch [query]
       (let [a (r/atom nil)]
         (ajax/request
           {:method :post
            :uri "/api/query"
            :params {:query query}
            :on-success (fn [data]
                          (reset! a data))})
         a))))
