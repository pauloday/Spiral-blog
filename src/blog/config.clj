(ns blog.config)

;; This is not the config file to change the config in. All this
;; contains is the default values that should never be used, and some
;; generator defs for convenience in the rest of the code. Make a
;; seperate config file with the values below and use that to generate
;; the blog instead.

(def *author*
  "Author")

(def *email*
  "Email")

;; Displayed in the taskbar of the browser
(def *title*
  "Title")

;; All files ending with this extension in the posts folder are
;; treated as posts
(def *post-extension*
  ".txt")

;; The folder to output the blog to, it should also contain the
;; resources file
(def *out-folder*
  "out-folder")

;; paths are reletive to the out-folder
(def *out-css-file*
  "resources/public/css/style.css")

;; The homepage file
(def *out-html-file*
  "index.html")

;; The folder thatcontains all the posts
(def *post-files-folder*
  "posts/")

;; The folder that contains all the HTML for each post page
(def *post-files-out-folder*
  "posts/out/")

;; The folder that contains all the html for each tag page
(def *tags-folder*
  "tags/")

;; The logo to display in the sidebar. Must be a image
(def *logo*
  "resources/img/spiral.png")

;; The url for the favicon
(def *favicon*
  "http://dl.dropbox.com/u/7568291/favicon.ico")

;; The sidebar items. A list of [title content] where title is the
;; text that is displayed, and content is the link or type of
;; text. Content can be one of :tags or :title. :tags makes a title
;; with text title, then a list of all tags, as links to their
;; respective tags page. Title just makes "title" a plain-text title
(def *sidebar-items*
  ["Tags" :tags
   "On Github" :title
   "This Site" "https://github.com/ertdfgcb/"])

;; The text to display below the links box. newlines are ignored
(def *about-text*
  "About text")

;; For convenience in the rest of the code. All this does is make all
;; the reletive paths above into absolute paths for the rest of the
;; code to use.

(defn redef-config [config]
  (load-string config)

  (def *out-css-path*
    (str *out-folder* *out-css-file*))

  (def *out-html-path*
    (str *out-folder* *out-html-file*))

  (def *posts-folder*
    (str *out-folder* *post-files-folder*))

  (def *posts-out-folder*
    (str *out-folder* *post-files-out-folder*))

  (def *tags-out-folder*
    (str *out-folder* *tags-folder*)))

(redef-config "")