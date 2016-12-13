(ns logtest.core
  (:require [logtest.components :as components]
            [logtest.system :as system]
            [clojure.string :refer [blank?]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [com.stuartsierra.component :as c]
            [clj-log.core :refer [log]]))

(defroutes app-routes
  (GET "/" [] (fn [request] "Hello, World")))

(def app
  app-routes)

(defn- get-env
  [e default]
  (let [env-var (java.lang.System/getenv e)]
    (if (blank? env-var)
      default
      env-var)))

(defn start
  []
  (alter-var-root #'system/the-system c/start))

(defn stop
  []
  (alter-var-root #'system/the-system #(when % (c/stop %) nil)))

(def options (atom {:verbosity (keyword (get-env "VERBOSITY" "debug"))}))

(defn init
  []
  (alter-var-root #'system/the-system (constantly (components/system @options)))
  (start))
