(ns {{name}}.view
  (:require 
    [reagent.core :as reagent]))

(defn page []
  [:div "{{name}} main div"])

(defn main []
  (js/console.log "Welcome to [{{name}}], happy hacking!") 
  (reagent/render-component [page] (.getElementById js/document "main")))
