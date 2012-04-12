(ns blog.config) ;; Config must be in blog.config

;; These are all the configuration values that the generator uses. All
;; of them must be defined, and you can add arbitrary clojure code,
;; since this file is simply executed as clojure code by the
;; generator. It is read using "slurp", so make sure the size of the
;; file does not exceed the size of your RAM (somehow).

(def *author* "Author")

(def *email* "Email")

;; Displayed in the taskbar of the browser
(def *title* "Title")

;; Timezone offset from UTC
(def *tz* 8)

;; All files ending with this extension in the posts folder are
;; treated as posts
(def *post-extension* ".txt")

;; The folder to output the blog to, it should also contain the
;; resources file
(def *out-folder* "out-folder/")

;; The homepage file
(def *out-html-file* "index.html")

;; The folder thatcontains all the posts
(def *post-files-folder* "posts/")

;; The folder that contains all the HTML for each post page
(def *posts-out-folder* "posts/")

;; The folder that contains all the html for each tag page
(def *tags-folder* "tags/")

;; The url for the favicon
(def *favicon* "http://dl.dropbox.com/u/7568291/favicon.ico")

;; The links. A list of {:title "title" :desc "desc" :href "href"}
;; where title is the text that is displayed, href is the link, and
;; desc is the description displayed next to the link.
(def *links*
  [{:title "This Site"
    :href "https://github.com/ertdfgcb/"
    :desc "The code for generating this blog on Github"}])

;; The text to display below the links box. newlines are ignored
(def *about-text* "About text")

;; Extra pages in the form [:file "file" :out "out]. These are
;; generated without a date at the location at :out
(def *extra-pages* [{:file "extra/404.md"
                     :out "404.html"}])

;; This is included in every page. Can be used to include
;; css/javascript/whatever for each page. Can be either a string of
;; valid html, or a hiccup data structure, like it is here.
(def *includes*  (list [:link {:rel "stylesheet" ;; main stylesheet
                               :type "text/css"
                               :href "/resources/css/style.css"}]
                         [:link {:rel "shortcut icon"
                                 :type "image/x-icon"
                                 :href *favicon*}]
                         [:link {:href
                                 "/resources/google-code-prettify/prettify.css"
                                 :type "text/css"
                                 :rel "stylesheet"}]
                         [:script {:type "text/javascript"
                                   :src
                                   "/resources/prettify/prettify.js"}]
                         [:script {:type "text/javascript"
                                   :src
                                   "/resources/prettify/lang-clj.js"}]))