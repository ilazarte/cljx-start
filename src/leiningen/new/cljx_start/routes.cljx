(ns {{name}}.routes
  #+clj
  (:require
    [environ.core              :refer [env]]
    [compojure.core            :refer [defroutes GET]] 
    [compojure.handler         :as handler]
    [compojure.route           :as route]
    [ring.middleware.file      :as file]
    [ring.middleware.file-info :as file-info]
    [ring.middleware.json      :as json]
    [ring.middleware.resource  :as resource]
    [ring.middleware.content-type :as content-type]
    [ring.util.response        :as response]
    [{{name}}.view                 :as view]))

#+clj
(defn wrap-nocache 
  "disable client cache, helps with source map issues."
  [handler]
  (fn [request]
    (let [response (handler request)]
    (-> response
      (assoc-in [:headers "Cache-Control"] "no-cache, no-store, must-revalidate")
      (assoc-in [:headers "Pragma"] "no-cache")
      (assoc-in [:headers "Expires"] "0")))))

#+clj
(defroutes server
  
  (GET "/" [] 
    (view/index))

  (GET "/css/{{name}}.css" [] 
    {:headers {"Content-Type" "text/css"}
     :body    (view/css)})
  
  (GET "/example/json" [params]
    {:body (range 1 10)})
  
  (route/resources "/")
  
  (route/not-found (view/file-not-found)))

#+clj
(def app
  (let [wrapper (-> (handler/site server)
                  (resource/wrap-resource "/META-INF/resources")
                  (resource/wrap-resource "/public")
                  (content-type/wrap-content-type)
                  json/wrap-json-body
                  json/wrap-json-params
                  json/wrap-json-response)]
    (if (= :dev (env :profile))
      (wrap-nocache wrapper)
      wrapper)))
