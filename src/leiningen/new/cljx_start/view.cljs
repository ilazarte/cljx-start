(ns {{name}}.view
  (:require 
    [reagent.core :as reagent]
    [{{name}}.macros :as macros]))

(defn page []
  [:div "{{name}} main div"])

(defn main []
  (macros/log "happy hacking!")
  (reagent/render-component [page] (macros/get-element-by-id "main")))