(ns leiningen.utils
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def ^:private default-options {:targets []
                                :matches :all
                                :style :compressed
                                :watch false
                                :source-maps false})

(defn normalize-options
  [options]
  (merge default-options options))

(defn delete-file!
  [file]
  (when (.exists file)
    (println (str "Deleting: " file))
    (when (.isDirectory file)
      (run! delete-file! (.listFiles file)))
    (io/delete-file file)))

(defn targets->str [targets]
  (str/join " " targets))
