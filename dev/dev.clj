(ns dev
  (:require [com.stuartsierra.component.repl :as component-repl]
            [clj-api.core :as core]))

(component-repl/set-init
  (fn [_old-systtem]
    (core/api-system {:server {:port 3001}})))
