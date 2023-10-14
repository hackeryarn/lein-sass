(ns leiningen.render
  (:require [leiningen.utils :as utils]
            [clojure.java.shell :as shell]))

(defn build-command-vec [{:keys [targets style source-maps watch]}]
  (let [sass-style (name style)
        opts (cond-> ["--update" "--style" sass-style]
               (not source-maps) (conj "--no-source-map")
               watch (conj "--watch"))]
    (concat ["sass"] opts targets)))

(defn render
  [options]
  (let [opts-vec (build-command-vec options)]
    (println (str "  [sass] - " (utils/targets->str (:targets options))))
    (println (:err (apply shell/sh opts-vec)))))
