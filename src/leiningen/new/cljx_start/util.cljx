(ns {{name}}.util
  #+cljs 
  (:require-macros
    [{{name}}.util :refer [log
                           get-element-by-id]]))

(defmacro log [& forms]
  `(js/console.log "[{{name}}]" (js/Date.) "> " ~@forms))

(defmacro get-element-by-id [id]
  `(js/document.getElementById ~id))