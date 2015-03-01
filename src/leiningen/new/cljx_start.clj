(ns leiningen.new.cljx-start
  (:require [leiningen.new.templates :refer [renderer     
                                             multi-segment 
                                             sanitize-ns
                                             project-name
                                             year
                                             sanitize     
                                             ->files]]
            [leiningen.core.main :as main]))

(defn cljx-start
  "Minimal cljx lein template with a few conveniences"
  [name]
  (let [render    (renderer "cljx-start")
        main-ns   (multi-segment (sanitize-ns name))
        dir       (java.io.File. (project-name name))
        create    #(let [pdir (java.io.File. dir %)]
                     (.mkdirs pdir))
        sanitized (sanitize (project-name name))
        data      {:name        (project-name name)
                   :namespace   main-ns
                   :sanitized   sanitized
                   :year        (year)}
        subdirs   ["src/clj"]]
    
    (main/info "Generating" name "using cljx-start")
    (main/info "Creating project files...")
    
    (->files 
      data
      [".gitignore"                        (render ".gitignore" data)]
      ["README.md"                         (render "README.md" data)]
      ["LICENSE"                           (render "LICENSE" data)]
      ["project.clj"                       (render "project.clj" data)]
      ["src/cljx/{{sanitized}}/core.cljx"  (render "core.cljx" data)]
      ["src/cljs/{{sanitized}}/view.cljs"  (render "view.cljs" data)]
      ["dev/clj/cljx_start/core.clj"       (render "core.clj" data)]
      ["dev/cljs/cljx_start/dev.cljs"      (render "dev.cljs" data)])
    
    (main/info "Creating empty clj/cljs directories...")
    (doseq [subdir subdirs] 
      (create (str subdir "/" sanitized)))
    (main/info "Done!")))