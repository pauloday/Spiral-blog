(ns blog.config)

;; This is not the config file to change the config in. All this
;; contains is the default values that should never be used, and some
;; generator defs for convenience in the rest of the code. Use
;; "example-conf.clj" in the main directory for your configuration(s)

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

;; The sidebar items. A list of [title content] where title is the
;; text that is displayed, and content is the link or type of
;; text. Content can be one of :tags or :title. :tags makes a title
;; with text title, then a list of all tags, as links to their
;; respective tags page. Title just makes "title" a plain-text title
(def *links*
  ["This Site" "https://github.com/ertdfgcb/" "The code for generating this blog on Github"])

;; The text to display below the links box. newlines are ignored
(def *about-text* "About text")

(def *extra-pages* ["extra/extra-page.md" "extra-page.html"])

;; For convenience in the rest of the code. All this does is make all
;; the reletive paths above into absolute paths for the rest of the
;; code to use.

(defn redef-config [config]
  (load-string config)

  (def *out-css-path*
    (str *out-folder* *out-css-file*))

  (def *out-html-path*
    (str *out-folder* *out-html-file*))

  (def *posts-path*
    (str *out-folder* *post-files-folder*))

  (def *posts-out-path*
    (str *out-folder* *posts-out-folder*))

  (def *tags-out-path*
    (str *out-folder* *tags-folder*)))

(redef-config "")