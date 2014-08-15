(defproject {{name}} "0.1.0-SNAPSHOT"

  :description "FIXME: write this!"
  
  :url "http://example.com/FIXME"

  :scm {:name "git"
        :url "https://github.com/user/repo"}
  
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2280"]]
  
  :plugins [[com.keminglabs/cljx "0.4.0" :exclusions [org.clojure/clojure]]
            [lein-cljsbuild "1.0.4-SNAPSHOT"]
            [lein-pdo "0.1.1"]]

  :jar-exclusions [#"\.cljx|\.svn|\.swp|\.swo|\.DS_Store"]
  
  :resource-paths ["target/generated/classes"]
  
  :prep-tasks [["cljx" "once"] ["cljsbuild" "once"] "javac" "compile"]
  
  :source-paths ["src/cljx" "src/clj" "src/cljs"]

  :clean-targets [:target-path :compile-path "out"]
  
  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path "target/classes"
                   :rules :clj}
                  
                  {:source-paths ["src/cljx"]
                   :output-path "target/generated/classes"
                   :rules :cljs}]}
  
  :cljsbuild {:builds {:{{name}} 
                       {:source-paths ["src/cljs" "target/generated/classes"]
                        :compiler {:output-to "out/{{sanitized}}.js"
                                   :source-map "out/{{sanitized}}.js.map"
                                   :output-dir "out" 
                                   :optimizations :none}}}}
  
  :profiles {:dev {:dependencies [[compojure "1.1.8"]
                                  [ring "1.3.0"]
                                  [ring/ring-json "0.3.1"]
                                  [hiccup "1.0.5"]]
                   
                   :plugins [[lein-ring "0.8.11"]]
                   
                   :source-paths ["dev/clj" "dev/cljs"]
                   
                   :cljsbuild {:builds {:{{name}} {:source-paths ["dev/cljs"]}}}
                   
                   :aliases {"rhino"    ["trampoline" "cljsbuild" "repl-rhino"]
                             "once"     ["do" "cljx" "once," "cljsbuild" "once"]
                             "auto"     ["pdo" "cljx" "auto," "cljsbuild" "auto"] 
                             "headless" ["ring" "server-headless" "8080"]
                             "server"   ["ring" "server" "8080"]
                             "dev"      ["pdo" "server," "cljx" "auto," "cljsbuild" "auto"]}
              
                   :ring {:handler       cljx-start.core/app
                          :auto-reload?  true
                          :auto-refresh? true}}})
