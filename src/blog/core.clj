(ns blog.core
  (:use blog.views
        blog.config
        hiccup.core
        [clojure.string :only [split]]
        [clj-time.core :only [now from-time-zone
                              time-zone-for-offset year before?]]
        [clj-time.format :only [formatter unparse parse]]
        [clojure.java.io :only [file]])
  (:import
           [org.pegdown PegDownProcessor Extensions])
  (:gen-class :main true))

(defn md-to-html [txt]
  (let [pd ( PegDownProcessor. Extensions/SMARTS)]
    (.markdownToHtml pd txt)))

(defn- process-post [txt]
  (if (= "$D" (take 2 txt))
    nil
    (let [lines (split txt #"\n")
          time (from-time-zone (now) (time-zone-for-offset *tz*))
          date (unparse (formatter "MMMddYYYY") time)
          mk-struct #(hash-map
                      :date (first %)
                      :title (second %)
                      :tags (split (nth % 2) #"\s")
                      :body (md-to-html (apply str
                                               (interpose "\n" (drop 3 %)))))]
      (if (= \$ (first (first lines)))
        (mk-struct (cons date lines)))
      (mk-struct lines))))

(defn read-post [file]
  (process-post (slurp file)))

(defn- write-post [post]
  (spit (make-post-name post) (post-page post)))

(defn- write-tag [tag]
  (spit (make-tag-name tag) (tag-page tag)))

(defn- write-extra [extra]
  (spit (str *out-folder* (:out extra))
        (page (read-post (str *out-folder* (:file extra))) false "")))

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

(defn- sort-posts [posts]
  (sort-by #(parse (formatter "MMMddYYYY") (:date %)) before? posts))

(defn update-blog []
  (let [is-post
        #(and (not (.isDirectory %))
              (= *post-extension*
                 (apply str (take-last
                             (count *post-extension*)
                             (.getName %)))))
        files (filter is-post (file-seq (file *posts-path*)))
        posts (filter identity
                      (reverse (map read-post files)))
        ;; recent is on top
        tags (add-posts-tags (get-post-tags posts) posts)]
    (println "Saving home page to: " *out-html-path*)
    (spit *out-html-path* (home-page posts))
    (println "Saving posts to: " *posts-out-path*)
    (dorun (map write-post posts))
    (println "Saving tags to: " *tags-out-path*)
    (dorun (map write-tag tags))
    (if (> (count *links*) 9)
      (do (concat *links* ["More" "links.html" ""])
          (println "*Links list longer than 3 items*")
          (println "Saving links page to: " (str *out-folder* "links.html"))
          (spit (str *out-folder* "links.html") (links-page))))
    (println "Generating extra pages")
    (dorun (map write-extra *extra-pages*)))
  (println "Blog generated in " *out-folder*))

(defn main [& args]
  (if args
    (do (println "Reading config file from: " (first args))
        (redef-config (slurp (first args)))))
  (update-blog))

(defn -main [& args]
  (apply main args))
