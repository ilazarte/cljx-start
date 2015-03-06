(ns {{name}}.main
  #+clj
  (:require
    [ring.adapter.jetty :as jetty]
    [{{name}}.routes        :as routes])
  #+cljs
  (:require 
    [figwheel.client :as figwheel :include-macros true]
    [{{name}}.view :as view])
  #+clj
  (:gen-class))

#+clj
(defn -main [& args]
  (jetty/run-jetty routes/app {:port 80}))

#+cljs
(enable-console-print!)

#+cljs
(figwheel/watch-and-reload
  :websocket-url "ws://localhost:3449/figwheel-ws"
  :jsload-callback (fn [] (view/main)))

#+cljs
(view/main)