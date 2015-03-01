(defproject {{name}} "0.1.0-SNAPSHOT"

  :description "FIXME: write this!"
  
  :url "http://example.com/FIXME"

  :scm {:name "git"
        :url "https://github.com/user/repo"}
  
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2760"]
                 [figwheel "0.2.2-SNAPSHOT"]
                 [compojure "1.3.1"]
                 [ring "1.3.2"]
                 [ring/ring-json "0.3.1"]
                 [hiccup "1.0.5"]
                 [org.webjars/react "0.11.1"]
                 [reagent "0.4.3"]]
  
  :plugins [[com.keminglabs/cljx "0.5.0"]
            [lein-cljsbuild "1.0.4"]
            [lein-figwheel "0.2.2-SNAPSHOT"]
            [lein-pdo "0.1.1"]]

  :jar-exclusions [#"\.cljx|\.svn|\.swp|\.swo|\.DS_Store"]
  
  :source-paths ["src/cljx" "src/clj" "src/cljs"]

  :figwheel {:port 3449}
  
  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path "target/classes"
                   :rules :clj}
                  
                  {:source-paths ["src/cljx"]
                   :output-path "target/generated/classes"
                   :rules :cljs}]}
  
  :cljsbuild {:builds {:{{name}} 
                       {:source-paths ["src/cljs" "target/generated/classes"]
                        :compiler {:output-to "resources/public/js/{{sanitized}}.js"
                                   :source-map "resources/public/js/{{sanitized}}.js.map"
                                   :output-dir "resources/public/js" 
                                   :optimizations :none}}}}
  
  :profiles {:dev {:plugins [[lein-ring "0.9.1"]]
                   
                   :source-paths ["dev/clj" "dev/cljs"]
                   
                   :cljsbuild {:builds {:{{name}} {:source-paths ["dev/cljs"]}}}
                   
                   :aliases {"once" ["do" "cljx" "once," "cljsbuild" "once"]
                             "auto" ["pdo" "cljx" "auto," "cljsbuild" "auto"]
                             "dev"  ["pdo" "ring" "server-headless" "8080," "cljx" "auto," "figwheel"]}
              
                   :ring {:handler       cljx-start.core/app
                          :auto-reload?  false
                          :auto-refresh? false}}})
