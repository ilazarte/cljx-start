(ns {{name}}.view
  #+clj
  (:require
    [hiccup.page :refer [html5 
                         include-js 
                         include-css]]
    [garden.core :as garden])
  #+cljs
  (:require 
    [reagent.core :as reagent]
    [{{name}}.util :as util]))

#+clj
(defn render
  "render a hiccup form"
  [& forms]
  (html5 
     [:head 
      (include-css 
        "/css/{{name}}.css")]
     [:body
      forms 
      (include-js 
         "/js/goog/base.js" 
         "/js/{{sanitized}}.js")
      [:script "goog.require('{{sanitized}}.main');"]]))

#+clj
(defn index []
  (render [:div 
           "hello from hiccup!" 
           [:div#main nil]]))

#+clj
(defn file-not-found []
  (render [:div "File not found!"]))

#+clj
(defn css []
  (garden/css [:body {:font-size "12px"}]))

#+cljs
(defn page []
  [:div "hello from reagent!"])

#+cljs
(defn main []
  (util/log "happy hacking!")
  (reagent/render-component [page] (util/get-element-by-id "main")))