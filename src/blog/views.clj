(ns blog.views
  (:use hiccup.core
        blog.config)
  (:import [java.net URLEncoder]))

(defn make-post-name [post]
  (str *posts-out-folder* (URLEncoder/encode (post :title)) ".html"))

(defn make-tag-name [tag]
  (str *tags-out-folder*  (URLEncoder/encode (tag :name)) ".html"))

(defhtml footer []
  [:div#footer
   "Site generated by "
   [:a.footer-link {:href "http://clojure.org/"}
    "Clojure"]
   " and "
   [:a.footer-link {:href "http://github.com/weavejester/hiccup"} "Hiccup"]])

(defhtml page-prelude [& body]
  [:link {:rel "stylesheet"
          :type "text/css"
          :href *out-css-path*}]
  [:link {:rel "shortcut icon"
          :type "image/x-icon"
          :href *favicon*}]
  [:link {:href "resources/google-code-prettify/prettify.css"
          :type "text/css"
          :rel "stylesheet"}]
  [:script {:type "text/javascript"
            :src "resources/google-code-prettify/prettify.js"}]
  [:script {:type "text/javascript"
            :src "resources/google-code-prettify/lang-clj.js"}]
  [:title *title*]
  [:body
   { :onload "prettyPrint()"}
   [:div#content
    body
    (footer)]])

(defhtml author-link []
  [:h3#author-title
   [:a#author-link {:href *out-html-path*} *author*]])

(defhtml tag-link [name]
  [:a.tag-link {:href (str *out-folder* *tags-folder* name ".html")} name])

(defhtml tags [tags]
  [:h5#tags
      "More on "
   (apply str (interpose " " (map tag-link tags)))])

(defhtml post-list [post]
  [:div.post-listing
   [:h4#post-list-title
    [:a {:href (make-post-name post)}
     (:title post)]]
   [:p#date
    [:span#month
     (-> post :date first)]
    [:br]
    [:span#day
     (-> post :date second)]
    [:br]
    [:span#year
     (nth (:date post) 2)]]])

(defn make-link [[text link desc]]
  (html [:a.link-item {:href link} text]
        [:div#link-desc " - " desc [:br]]))

(defhtml home-page [posts]
  ;; posts is a vector of maps {:title "" :tags
  ;; [""] :body ""}
  (page-prelude
   [:h1#author-title
    *author*]
   [:div#info
    [:ul.info-row
     [:li#about.info-cell
      [:div
       [:h2
        "About"]
       *about-text*]]
     [:li#links.info-cell
      [:div
       [:h2
        "Links"]
       (map make-link (partition 3 *sidebar-items*))]]]]
   [:div#posts
    [:h3
     "Posts"]
    (map post-list posts)]))

(defhtml post-page [post]
  (page-prelude
   (author-link)
   [:h1
    (:title post)]
   [:div#post
    (:body post)]
   (tags (:tags post))))

(defhtml tag-page [tag]
  (page-prelude
   (author-link)
   [:h1
    (:name tag)]
   [:div#posts
    (map post-list (:posts tag))]))