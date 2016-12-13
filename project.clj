(defproject logtest "0.0.0-dev"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-logging-config "1.9.12"]
                 [clj-log "0.4.5"]
                 [com.stuartsierra/component "0.3.1"]]
  :plugins [[lein-ring "0.9.7"]]
  :profiles {:dev {:dependencies [[proto-repl "0.3.1"]]}}
  :ring {:handler logtest.core/app
         :init logtest.core/init
         :destroy logtest.core/stop})
