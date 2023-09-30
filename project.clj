(defproject lein-sass "0.6.0"
  :description "SASS autobuilder plugin"
  :url "https://github.com/tuhlmann/lein-sass"
  :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.cemerick/pomegranate "0.2.0"]
                 [clojure-watch "LATEST"]
                 [me.raynes/fs "1.4.6"]]

  :profiles {:dev {:dependencies [[speclj "2.5.0"]]
                   :plugins [[speclj "2.5.0"]]
                   :test-paths ["spec/"]}

             :spec {:sass {:src "spec/files"
                           :output-directory "spec/out"}}

             :spec-map {:sass {:src "spec/files"
                               :source-maps true
                               :output-directory "spec/out/map"}}}

  :hooks [leiningen.sass]

  :eval-in-leiningen true
  :min-lein-version "2.0.0"
  )
