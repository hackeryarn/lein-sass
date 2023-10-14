(ns leiningen.integration-test
  (:require [leiningen.utils :as utils]
            [clojure.test :as test]
            [clojure.java.shell :as shell]
            [clojure.java.io :as io]))

(defn clean-out-fixture [f]
  (with-out-str
    (utils/delete-file! (io/file "test/out")))
  (f))

(test/use-fixtures :each clean-out-fixture)

(defn sass [profile sub-task]
  (shell/sh "lein" "with-profile" profile "sass" sub-task))

(test/deftest integration
  ;; This is not ideal but provides some way of testing the tasks (given
  ;; that I have figured out how to include leiningen dependencoes in
  ;; the tests) especially: we are relying on the project.clj file
  ;; (which can't be changed from here)
  (test/testing "without source maps"
    (test/testing "once"
      (test/testing "compiles the files in the correct directory"
        (sass "spec" "once")
        (let [out-files (file-seq (io/file "test/out"))]
          (test/is (= 3 (count out-files))))
        (let [file-content (slurp "test/out/foo.css")
              expected-content ".wide{width:100%}.foo{display:block}\n"]
          (test/is (= expected-content file-content)))
        (let [file-content (slurp "test/out/bar.css")
              expected-content ".bar{display:none}\n"]
          (test/is (= expected-content file-content)))))
    (test/testing "clean"
      (test/testing "removes all artifacts that were created by sass task"
        (sass "spec" "once")
        (test/is (.exists (io/file "test/out")))
        (sass "spec" "clean")
        (test/is (not (.exists (io/file "test/out")))))))

  (test/testing "with source maps"
    (test/testing "once"
      (test/testing "compiles the files in the correct directory"
        (sass "spec-map" "once")
        (let [out-files (file-seq (io/file "test/out/map"))]
          (test/is (= 5 (count out-files))))
        (let [file-content (slurp "test/out/map/foo.css")
              expected-content ".wide{width:100%}.foo{display:block}/*# sourceMappingURL=foo.css.map */\n"]
          (test/is (= expected-content file-content)))
        (test/is (.exists (io/file "test/out/map/foo.css.map")))
        (let [file-content (slurp "test/out/map/bar.css")
              expected-content ".bar{display:none}/*# sourceMappingURL=bar.css.map */\n"]
          (test/is (= expected-content file-content)))
        (test/is (.exists (io/file "test/out/map/bar.css.map"))))
      (test/testing "removes all artifacts that were created by sass task"
        (sass "spec-map" "once")
        (test/is (.exists (io/file "test/out/map")))
        (sass "spec-map" "clean")
        (test/is (not (.exists (io/file "test/out/map"))))))))
