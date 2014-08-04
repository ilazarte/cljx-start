# cljx-start

cljx-start is a Leiningen template to provide a configured cjlx template to ease in the configuration nightmare.

## Difference from cljs-start

cljx-start isn't intended to have a comprehensive release setup, it focuses more on getting you compiled and coding.
Most all repl concepts have been removed except for a non-project rhino repl (see aliases).
This is more from a "get it out there" perspective than anything else. 
  
## Usage

tl;dr lein once, then lein dev and code away

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

    lein rhino ; start a cljs repl that does not contain the project source
    lein once ; preprocess cljx into clj and cljs and build once
    lein auto ; setup auto building of cljx into clj and cljs
    lein clean ; clean the output directories of all generated and build targets
    lein server ; launch ring in server mode (pops up browser)
    lein headless ; launch ring in server headless mode (does not popup browser)
    lein dev ; the grand enchilada, launch cljx/cljsbuild in auto mode and popup a server
    
## Plans

Looking into including figwheel potentially, possibly gorilla repl as well.    
    
## License

Copyright © 2014 ilazarte Released under the Eclipse Public License, the same as Clojure.