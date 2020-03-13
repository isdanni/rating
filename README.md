# ratings

A server side web app built using Clojure and Duct framework.

### Why Duct?

- [link](https://github.com/duct-framework/core)
- It is a **modular** framework. 
- Easy to build & efficient to manage different modules.

## Setup

After clone to local:

```sh
lein duct setup
lein repl
```

```clojure
user=> (dev)
:loaded
```

Run `go` to prep and initiate the system.

```clojure
dev=> (go)
:duct.server.http.jetty/starting-server {:port 3000}
:initiated
```

By default this creates a web server at <http://localhost:3000>.

When you make changes to your source files, use `reset` to reload any
modified files and reset the server.

```clojure
dev=> (reset)
:reloading (...)
:resumed
```

### Testing

Testing is fastest through the REPL, as you avoid environment startup
time.

```clojure
dev=> (test)
...
```

But you can also run tests through Leiningen.

```sh
lein test
```