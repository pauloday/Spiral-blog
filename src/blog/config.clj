(ns blog.config)

;; For convenience in the rest of the code. All this does is make all
;; the reletive paths above into absolute paths for the rest of the
;; code to use.

(def out-folder "")
(def out-html-file "")
(def posts-out-folder "")
(def post-files-folder "")
(def tags-folder "")
(def email "")
(def includes "")
(def author "")
(def title "")
(def about-text "")
(def links "")
(def tz 8)
(def post-extension "")
(def extra-pages "")

(defn redef-config []

  (def out-html-path
    (str out-folder out-html-file))

  (def posts-path
    (str out-folder post-files-folder))

  (def posts-out-path
    (str out-folder posts-out-folder))

  (def tags-out-path
    (str out-folder tags-folder)))