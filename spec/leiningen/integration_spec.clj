(ns leiningen.integration-spec
  (:use [speclj.core]
        [clojure.java.shell :only [sh]])
  (:require [leiningen.utils :as utils]
            [clojure.java.io :as io]))

(describe "integration tests on tasks"
  ;; This is not ideal but provides some way of testing the tasks (given
  ;; that I have figured out how to include leiningen dependencoes in
  ;; the tests) especially: we are relying on the project.clj file
  ;; (which can't be changed from here)

          (before (with-out-str
                    (utils/delete-file! (io/file "spec/out"))))

          (defn sass [profile sub-task] (sh "lein" "with-profile" profile "sass" sub-task))

          (context "without source maps"

            (context "once"
              (it "compiles the files in the correct directory"
                  (sass "spec" "once")

                  (let [out-files (file-seq (io/file "spec/out"))]
                    (should= 3 (count out-files)))

                  (let [file-content (slurp "spec/out/foo.css")
                        expected-content ".wide{width:100%}.foo{display:block}\n"]
                    (should= expected-content file-content))

                  (let [file-content (slurp "spec/out/bar.css")
                        expected-content ".bar{display:none}\n"]
                    (should= expected-content file-content))))

            (context "clean"
              (it "removes all artifacts that were created by sass task"
                  (sass "spec" "once")
                  (should (.exists (io/file "spec/out")))

                  (sass "spec" "clean")
                  (should-not (.exists (io/file "spec/out"))))))
          (context "with source maps"
            (context "once"
              (it "compiles the files in the correct directory"
                  (sass "spec-map" "once")

                  (let [out-files (file-seq (io/file "spec/out/map"))]
                    (should= 5 (count out-files)))

                  (let [file-content (slurp "spec/out/map/foo.css")
                        expected-content ".wide{width:100%}.foo{display:block}/*# sourceMappingURL=foo.css.map */\n"]
                    (should= expected-content file-content))
                  (should (.exists (io/file "spec/out/map/foo.css.map")))

                  (let [file-content (slurp "spec/out/map/bar.css")
                        expected-content ".bar{display:none}/*# sourceMappingURL=bar.css.map */\n"]
                    (should= expected-content file-content))
                  (should (.exists (io/file "spec/out/map/bar.css.map")))))

            (context "clean"
              (it "removes all artifacts that were created by sass task"
                  (sass "spec-map" "once")
                  (should (.exists (io/file "spec/out/map")))

                  (sass "spec-map" "clean")
                  (should-not (.exists (io/file "spec/out/map")))))))
