(defproject {{name}} "0.1.0-SNAPSHOT"

  :description "FIXME: write this!"
  
  :url "http://example.com/FIXME"

  :scm {:name "git"
        :url "https://github.com/user/{{name}}"}
  
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[environ "1.0.0"]
                 [org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2985"]
                 [compojure "1.3.2"]
                 [ring "1.3.2"]
                 [ring/ring-json "0.3.1"]
                 [garden "1.2.5"]
                 [hiccup "1.0.5"]
                 [figwheel "0.2.5-SNAPSHOT"]
                 [prismatic/schema "0.4.0"]
                 [org.webjars/jquery "2.1.3"]
                 [org.webjars/d3js "3.5.3"]
                 [org.webjars/materializecss "0.95.0"] 
                 [org.webjars/react "0.12.2"] 
                 [reagent "0.5.0-alpha3"]]
  
  :plugins [[lein-environ "1.0.0"]
            [com.keminglabs/cljx "0.6.0"]
            [lein-figwheel "0.2.5-SNAPSHOT"]
            [lein-cljsbuild "1.0.5"]
            [lein-pdo "0.1.1"]]

  :jar-exclusions [#"\.cljx|\.svn|\.swp|\.swo|\.DS_Store"]
  
  :source-paths ["src/cljx"] ;add src/clj src/cljs if used
  
  :figwheel {:port 3449}
  
  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path "target/classes"
                   :rules :clj}
                  
                  {:source-paths ["src/cljx"]
                   :output-path "target/generated/classes"
                   :rules :cljs}]}
  
  :cljsbuild {:builds {:{{name}} 
                       {:source-paths ["target/classes" "target/generated/classes"] ;add src/clj src/cljs if used
                        :compiler {:output-to "resources/public/js/{{name}}.js"
                                   :source-map "resources/public/js/{{name}}.js.map"
                                   :output-dir "resources/public/js" 
                                   :optimizations :none}}}}
  
  :profiles {:dev {:env {:profile :dev}
                   
                   :plugins [[lein-ring "0.9.2"]]
                   
                   :aliases {"once" ["do" "cljx" "once," "cljsbuild" "once"]
                             "auto" ["pdo" "cljx" "auto," "cljsbuild" "auto"]
                             "dev"  ["pdo" "ring" "server-headless" "8080," "cljx" "auto," "figwheel"]}
              
                   :ring {:handler       {{name}}.routes/app
                          :auto-reload?  true
                          :auto-refresh? false}}
             
             :prod {:env {:profile :prod}}})
