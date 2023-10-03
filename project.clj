(defproject lein-sass "1.0.0"
  :description "SASS autobuilder plugin"
  :url "https://github.com/hackeryarn/lein-sass"
  :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo}

  :dependencies [[org.clojure/clojure "1.11.1"]]

  :profiles {:dev {:dependencies [[speclj "3.4.3"]]
                   :plugins [[speclj "3.4.3"]]
                   :test-paths ["spec/"]}
             :spec {:sass {:targets ["spec/files:spec/out"]}}
             :spec-map {:sass {:targets ["spec/files:spec/out/map"]
                               :source-maps true}}}

  :hooks [leiningen.sass]
  :eval-in-leiningen true
  :min-lein-version "2.0.0")
