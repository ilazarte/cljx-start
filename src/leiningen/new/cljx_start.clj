(ns leiningen.new.cljx-start
  (:require [leiningen.new.templates :refer [renderer     
                                             multi-segment 
                                             sanitize-ns
                                             project-name
                                             year
                                             sanitize     
                                             ->files]]
            [leiningen.core.main :as main]))

; TODO, review multi-segment vs namespace vars candidates for removal
(defn cljx-start
  "Minimal cljx lein template with a few conveniences"
  [name]
  (let [render    (renderer "cljx-start")
        main-ns   (multi-segment (sanitize-ns name))
        sanitized (sanitize (project-name name))
        data      {:name        (project-name name)
                   :namespace   main-ns
                   :sanitized   sanitized
                   :year        (year)}]
    
    (main/info "Generating" name "using cljx-start")
    (main/info "Creating project files...")
    
    (->files 
      data
      [".gitignore"                         (render ".gitignore" data)]
      ["README.md"                          (render "README.md" data)]
      ["LICENSE"                            (render "LICENSE" data)]
      ["project.clj"                        (render "project.clj" data)]
      ["src/clj/{{sanitized}}/main.clj"     (render "main.clj" data)]
      ["src/cljx/{{sanitized}}/core.cljx"   (render "core.cljx" data)]
      ["src/cljx/{{sanitized}}/macros.cljx" (render "macros.cljx" data)]
      ["src/cljs/{{sanitized}}/view.cljs"   (render "view.cljs" data)]
      ["dev/clj/cljx_start/server.clj"      (render "server.clj" data)]
      ["dev/cljs/cljx_start/dev.cljs"       (render "dev.cljs" data)])
    
    (main/info "Done!")))