(ns logtest.components
  (:require [clj-logging-config.log4j :as clc]
            [clj-log.core :refer [log]]
            [clojure.tools.logging :as ctlog]
            [com.stuartsierra.component :as component]))

(log :info "file loaded")

(defrecord LoggingComponent [verbosity]
  component/Lifecycle
  (start [this]
    (log :info "in start; before logger is set")
    (ctlog/info "in start; before logger is set")

    (clc/set-logger! "logtest" :level verbosity)
    (clc/set-loggers! :root {:level :error})

    (log :info "in start; after logger is set")
    (ctlog/info "in start; after logger is set")

    this)
  (stop [this]
    (assoc this :verbosity nil)))

(defn system
  [{:keys [repl-port verbosity db-uri] :as options}]
  (log :info "starting system")
  (ctlog/info "starting system")
  (component/system-map
   :logging      (component/using (map->LoggingComponent {:verbosity verbosity}) [])))
