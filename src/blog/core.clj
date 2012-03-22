(ns blog.core
  (:use blog.views
        blog.config
        blog.showdown
        [blog.css :only [save-blog-css]]
        hiccup.core
        [clojure.string :only [split]]
        [clj-time.core :only [now from-time-zone time-zone-for-offset]]
        [clj-time.format :only [formatters unparse]]
        [clojure.contrib.duck-streams :only [read-lines write-lines]]
        [clojure.java.io :only [file]])
  (:import [java.net URLEncoder]
           [org.mozilla.javascript Context ScriptableObject])
  (:gen-class :main true))

(defn markdown-to-html [txt]
  (let [cx (Context/enter)
        scope (.initStandardObjects cx)
        input (Context/javaToJS txt scope)
        script (str *showdown-str*
                    "new Showdown.converter().makeHtml(input);")]
    (try
     (ScriptableObject/putProperty scope "input" input)
     (let [result (.evaluateString cx scope script "<cmd>" 1 nil)]
       (Context/toString result))
     (finally (Context/exit)))))

(defn- read-post [post]
  (let [lns (read-lines post)
        time (unparse (formatters :rfc822)
                      (from-time-zone (now) (time-zone-for-offset 8)))
        time-string (str (apply str (take 16 time))
                         " / "
                         (apply str (take 5 (nthnext time 17)))
                         " PST")
        lines (if (= \P (first (first lns)))
                lns
                (do (write-lines post (conj lns (str "P @ " time-string)))
                    (read-lines post)))]
    (if (= (second lines) "D")
      nil
      {:title (second lines) :tags (split (nth lines 2) #"\s")
       :date (apply str (nthnext (first lines) 3))
       :body (markdown-to-html
              (apply str (interpose "\n" (nthnext lines 3))))})))

(defn- write-thing [thing namefn pagefn]
  (spit (namefn thing) (pagefn thing)))

(defn- write-post [post]
  (write-thing post make-post-name post-page))

(defn- write-tag [tag]
  (write-thing tag make-tag-name tag-page))

(defn- get-post-tags [posts]
  (let [tags (distinct (mapcat :tags posts))
        vals (map #(hash-map :name % :n 0 :posts []) tags)]
    vals))

(defn- add-posts-tag [tag posts] ;;returns a tag with posts added
  (let [incn #(update-in % [:n] inc)
        addpost #(update-in % [:posts] (partial concat [%2]))
        tags? #(some #{(% :name)} (%2 :tags))]
    (reduce #(if (tags? % %2)
               (incn (addpost % %2))
            %) tag posts)))

(defn- add-posts-tags [tags posts]
  ;;takes list of empty tags and list of posts and adds posts to tags
  (map #(add-posts-tag % posts) tags))

(defn update-blog []
  (let [is-post
        #(and (not (.isDirectory %))
              (= *post-extension*
                 (apply str (take-last
                             (count *post-extension*)
                             (.getName %)))))
        files (filter is-post (file-seq (file *posts-folder*)))
        posts (filter identity
                      (reverse (map read-post files))) ;; reversed so the most
        ;; recent is on top
        tags (add-posts-tags (get-post-tags posts) posts)]
    (println "Saving CSS to: " *out-css-path* )
    (save-blog-css *out-css-path*)
    (def-sidebar tags)
    (println "Saving home page to: " *out-html-path*)
    (spit *out-html-path* (home-page posts))
    (println "Saving posts to: " *posts-out-folder*)
    (dorun (map write-post posts))
    (println "Saving tags to: " *tags-out-folder*)
    (dorun (map write-tag tags)))
  (println "Blog generated in " *out-folder*))

(defn main [& args]
  (if args
    (do (println "Reading config file from: " (first args))
        (load-file (first args))
        (redef-config (slurp (first args)))))
  (update-blog))

(defn -main [& args]
  (apply main args))