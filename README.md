Spiral-blog, Version 1.0
---

A simple blog engine written in clojure using
[Hiccup](www.github.com/weavejester/hiccup), and
[Gaka](www.github.com/briancarper/gaka). The goal is to be as minimal
as reasonable. The posts are stored as a flat database, and the entire
blog is static HTML.

Configuration
---

The configuration file is simply a clojure file that contains some
defs. All the defs in default.cfg must be included, and the
*out-folder* variable must be defined. All other values are the
hardcoded defaults. 

Usage
---

First, generate the jarfile

    lein uberjar

Then, run it, with the config file

    java -jar spiral-blog-standalone-1.0.jar config

The blog will be generated in the output folder defined in the config
file

License
---

See the LICENSE file.
