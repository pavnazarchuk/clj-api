(ns clj-api.core
  (:require [clj-api.config :as config])
  (:require [com.stuartsierra.component :as component]
            [clj-api.components.example-component
             :as example-component]
            [clj-api.components.pedestal-component :as pedestal-component]))

(defn api-system
  [config]
  (component/system-map :example-component (example-component/new-example-component
                                             config)
                        :pedestal-component
                        (component/using
                          (pedestal-component/new-pedestal-component config)
                          [:example-component])))

(defn -main
  []
  (let [system (-> (config/read-config)
                   (api-system)
                   (component/start-system)) ]
    (println "Starting API with config")
    (.addShutdownHook
      (Runtime/getRuntime)
      (new Thread #(component/stop-system system)))))
