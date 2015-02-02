# cljx-start

cljx-start is a Leiningen template to provide a configured cjlx template to ease in the configuration.
It incorporates reagent and figwheel for ease of development from the start.
It was inspired by cljs-start.

[![Clojars Project](http://clojars.org/cljx-start/lein-template/latest-version.svg)](http://clojars.org/cljx-start/lein-template)

## Difference from cljs-start

cljx-start isn't intended to have a comprehensive release setup, it focuses more on getting you compiled and coding.  This might change very soon as different profiles of the template are investigated.
  
## Usage

tl;dr lein once, then lein dev or lein auto and code away

long version:

If you are writing cross compiling code, it belongs in src/cljx.
Any target specific code is added to src/clj and as well as src/cljs.
For cljs, a bootstrap namespace is provided in dev/cljs defaulted to {projectname}.dev.
Require any namespace in that file that you wish to test in cljs and it will automatically add it.

The included web server has been setup as a ring compojure app with caching busted.  
This is important in order to make sure your web browser picks up the latest sources and executes them correctly.
Any new clj code in the ring app is automatically reloaded.
Any new cljs code added requires a page refresh.
As you are using cljx, you can spend more of your time testing functions in the repl.
I'm looking into making figwheel the primary feedback mechanism for cljs.

Several lein aliases have been created for convenience, I typically use lein dev and forget the rest.

    lein clean ; clean the output directories of all generated and build targets (does not delete figwheel output)
    lein once ; preprocess cljx into clj and cljs and build once
    lein auto ; start cljx and cljsbuild auto (useful for lib versions)
	lein dev ; start headless, start cljx auto, and run figwheel (browse to localhost:8080) 
        
## TODO
1. look into figwheel cleaning.  is it even necessary and if so what is the best way from lein cli.
2. investigate testing integration for both clj and cljs.
3. begin implementing various scenarios webapp, lib, script etc. 
    
## License

Copyright � 2014 ilazarte Released under the Eclipse Public License, the same as Clojure.