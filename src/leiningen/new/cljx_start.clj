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
  "cljx lib or isomorphic cljx based web app template"
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
      ["src/cljx/{{sanitized}}/main.cljx"   (render "main.cljx" data)]
      ["src/cljx/{{sanitized}}/routes.cljx" (render "routes.cljx" data)]
      ["src/cljx/{{sanitized}}/util.cljx"   (render "util.cljx" data)]
      ["src/cljs/{{sanitized}}/view.cljx"   (render "view.cljx" data)])
    
    (main/info "Done!")))