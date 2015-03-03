(ns {{name}}.macros
  #+cljs
  (:require-macros
    [{{name}}.macros :refer [log
                             get-element-by-id]]))

(defmacro log [& forms]
  `(js/console.log "[{{name}}]" (js/Date.) "> " ~@forms))

(defmacro get-element-by-id [id]
  `(js/document.getElementById ~id))