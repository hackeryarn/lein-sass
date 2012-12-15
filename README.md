# lein-haml-sass

Leiningen plugin to compile haml, sass, and scss files.

## Installation

You can install the pluggin by adding lein-haml-sass to your `project.clj` file in the `plugin` section:

```clj
(defproject example "1.2.3"
  :plugins [[lein-haml-sass "0.2.4"]])
```

Run the following command to download the library:

    $ lein deps

## configuration

The configuration for haml, sass, and scss is specified under the `:haml`, `:sass`, and `:scss` sections of your `project.clj.

Here is an example of `project.clj` with all the possible definitions.

```clj
(defproject example-project "1.2.3"
  :haml {:src "resources/haml"
         :output-directory "resources/public/html"
         ;; Other options (provided are default values)
         ;; :output-extension "html"
         ;; :auto-compile-delay 250
         ;; :delete-output-dir true ;; -> when running lein clean it will delete the output directory if it does not contain any file
         ;; :ignore-hooks [:clean :compile :deps] ;; -> if you use the hooks, this option allows you to remove some hooks that you don't want to run
         ;; :gem-version "3.1.7"
         }

  :sass {:src "resources/sass"
         :output-directory "resources/public/css"
         ;; Other options (provided are default values)
         ;; :output-extension "css"
         ;; :auto-compile-delay 250
         ;; :delete-output-dir true ;; -> when running lein clean it will delete the output directory if it does not contain any file
         ;; :ignore-hooks [:clean :compile :deps] ;; -> if you use the hooks, this option allows you to remove some hooks that you don't want to run
         ;; :gem-version "3.2.1"
         }

  :scss {:src "resources/scss"
         :output-directory "resources/public/css"
         ;; Other options (provided are default values)
         ;; :output-extension "css"
         ;; :auto-compile-delay 250
         ;; :delete-output-dir true ;; -> when running lein clean it will delete the output directory if it does not contain any file
         ;; :ignore-hooks [:clean :compile :deps] ;; -> if you use the hooks, this option allows you to remove some hooks that you don't want to run
         ;; :gem-version "3.2.1"
         }
    )
```

It is good to know that you only need to specify the section you plan to use.  So if you are only interested in haml just specify the `:haml` section.

By default lain-haml-sass will come bundled with haml gem version 3.1.7 and sass gem version 3.2.1.  However, if you like you can specify another gem version by using the `:gem-version` key for haml, sass, or scss subtasks.  lein-haml-sass will download the appropriate gem by using `lein <subtask> deps` or `lein deps` if you have configured the hooks.

## Usage

Four tasks available:

* haml: compiles haml files
* sass: compiles sass files
* scss: compiles scss files
* haml-sass: compiles haml, sass, and scss files depending on which
  file types are provided in the `project.clj`

For each task, three subtasks are availbale:

* once: compiles the source files once
* auto: keeps the compiler running and watches for new files and file changes
* clean: cleans the files that were generated by the compiler

Here is an example for haml:

Compile your files once:

    $ lein haml once

Let lein-haml-sass watch for changes in your haml sources files:

    $ lein haml auto

To delete all the files generated by lein-haml-sass:

    $ lein haml clean


## Hooks

The following hooks are supported by lein-haml-sass for all haml, sass, and scss types of file:

    $ lein compile
    $ lein clean

To enable the hooks, add the following lein to your `project.clj` file:

```clj
:hooks [leiningen.haml
        leiningen.sass
        leiningen.scss]
```

## TODO

* improve ensure-engine-started!
* document separate usage for lein1 and lein2
* add check for lein2 in lein1 version and fail appropriately
* document usage of project
* need to do some kind of perf test
* create some kind of CI to run against different versions of lein
* put colors in the terminal output

## License

Copyright (C) 2012 Renaud Tircher

Distributed under the Eclipse Public License, the same as Clojure.
