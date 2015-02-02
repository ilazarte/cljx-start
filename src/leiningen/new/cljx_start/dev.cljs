(ns {{name}}.dev
  (:require 
    [{{name}}.view :as view]
    [figwheel.client :as figwheel :include-macros true]))

(enable-console-print!)

(figwheel/watch-and-reload
  :websocket-url "ws://localhost:3449/figwheel-ws"
  :jsload-callback (fn [] (view/main)))

(view/main)