(defproject lein-sass "1.0.0"
  :description "SASS autobuilder plugin"
  :url "https://github.com/hackeryarn/lein-sass"
  :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo}
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :plugins [[com.github.clj-kondo/lein-clj-kondo "0.2.5"]]
  :profiles {:spec {:sass {:targets ["test/files:test/out"]}}
             :spec-map {:sass {:targets ["test/files:test/out/map"]
                               :source-maps true}}}
  :eval-in-leiningen true
  :min-lein-version "2.0.0")
