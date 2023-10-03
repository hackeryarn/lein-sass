(ns leiningen.cleaner
  (:require [leiningen.utils :as utils]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(defn target->dest-file [target]
  (-> target
      (str/split #":" 2)
      last
      io/file))

(defn clean-all!
  [{:keys [targets]}]
  (doseq [dest-file (map target->dest-file targets)]
    (utils/delete-file! dest-file)
    (utils/delete-file! (io/file (str (.getPath dest-file) ".map")))))
