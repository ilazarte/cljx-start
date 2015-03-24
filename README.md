# cljx-start

cljx-start is a Leiningen template to develop an isomorphic webapp via cljx.
It incorporates reagent, hiccup, garden, and figwheel for ease of development from the start.
It was inspired by cljs-start.

[![Clojars Project](http://clojars.org/cljx-start/lein-template/latest-version.svg)](http://clojars.org/cljx-start/lein-template)

## Difference from cljs-start

cljx-start isn't intended to have a comprehensive release setup, it focuses more on getting you compiled and coding.  This might change very soon as different profiles of the template are investigated.
  
## Usage

tl;dr lein once, then lein dev and code away.
lein dev will start a figwheel process and the embedded webapp.

long version:

The included web server has been setup as a ring compojure app with caching busted.  New clj code will require a page refresh
New cljx/cljs code will be automatically updated via figwheel The code includes sample css, server side html and clientside.
It only starts you with cljx clojure.  If you need clj/cljs, comments have been provided in the project.clj to show you where to start.

## Aliases

    lein clean ; clean generated and build targets (does not delete figwheel output)
    lein once ; preprocess cljx into clj and cljs and build once
	lein dev ; start headless, start cljx auto, and run figwheel (browse to localhost:8080) 
        
## TODO
1. look into figwheel cleaning.  is it even necessary and if so what is the best way from lein cli.
2. investigate testing integration for both clj and cljs.
3. begin implementing various scenarios webapp, lib, script etc.
     
## License

Copyright &copy; 2015 ilazarte Released under the Eclipse Public License, the same as Clojure.