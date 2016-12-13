# clojure-structured-logging
Minimal test case to demonstrate an issue with namespaced logging in Clojure.

## Problem

Logs using [clj-log](https://github.com/yogthos/clj-log) are tied to the `user` namespace; logs using `clojure.tools.logging` don't seem to have this issue.

## Output

```
$ lein ring server-headless
[logtest.components] {:ns "logtest.components",
 :time #inst "2016-12-13T15:43:02.774-00:00",
 :message "file loaded",
 :level :info}

[user] {:ns "user",
 :time #inst "2016-12-13T15:43:02.901-00:00",
 :message "starting system",
 :level :info}

[logtest.components] starting system
[user] {:ns "user",
 :time #inst "2016-12-13T15:43:02.910-00:00",
 :message "in start; before logger is set",
 :level :info}

[logtest.components] in start; before logger is set
[logtest.components] in start; after logger is set
2016-12-13 10:43:02.926:INFO:oejs.Server:jetty-7.6.13.v20130916
2016-12-13 10:43:02.961:INFO:oejs.AbstractConnector:Started SelectChannelConnector@0.0.0.0:3000
Started server on port 3000
```
