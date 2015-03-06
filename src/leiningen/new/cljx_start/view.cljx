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
        "/webjars/materializecss/0.95.0/css/materialize.css"
        "/css/{{name}}.css")]
     [:body
      forms 
      (include-js 
        "/webjars/jquery/2.1.3/jquery.js"
        "/webjars/materializecss/0.95.0/js/materialize.js"
        "/webjars/react/0.12.2/react.js"
        "/webjars/d3js/3.5.3/d3.js" 
        "/js/goog/base.js" 
        "/js/{{name}}.js")
      [:script "goog.require('{{name}}.main');"]]))

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