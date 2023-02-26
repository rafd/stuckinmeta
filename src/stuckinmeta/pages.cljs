(ns stuckinmeta.pages
  (:require
    [stuckinmeta.ui.landing]
    [stuckinmeta.ui.mission]
    [stuckinmeta.ui.home]
    [stuckinmeta.ui.hunter]))

(def pages
  [{:page/id :page/landing
    :page/view #'stuckinmeta.ui.landing/landing-page-view
    :page/path "/"}
   {:page/id :page/home
    :page/view #'stuckinmeta.ui.home/home-page-view
    :page/path "/home"}
   {:page/id :page/hunter
    :page/view #'stuckinmeta.ui.hunter/hunter-page-view
    :page/path "/hunter/:id"
    :page/parameters [:map
                      [:id string?]]}

   {:page/id :page/mission
    :page/view #'stuckinmeta.ui.mission/mission-page-view
    :page/path "/mission/:id"
    :page/parameters [:map
                      [:id string?]]}])
