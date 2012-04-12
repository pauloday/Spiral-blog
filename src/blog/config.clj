(ns blog.config)

;; For convenience in the rest of the code. All this does is make all
;; the reletive paths above into absolute paths for the rest of the
;; code to use.

(defn redef-config [config]
  (load-string config)

  (def *out-html-path*
    (str *out-folder* *out-html-file*))

  (def *posts-path*
    (str *out-folder* *post-files-folder*))

  (def *posts-out-path*
    (str *out-folder* *posts-out-folder*))

  (def *tags-out-path*
    (str *out-folder* *tags-folder*)))