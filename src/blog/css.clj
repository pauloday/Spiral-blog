(ns blog.css
  (:use gaka.core))

(def mrg "30px")
(def box-bcolor "#5e5e5e")
(def global-bcolor "#595959")
(def fcolor "#D7D7D7")
(def acolor "#ff9105")
(def lightacolor "#ffb35a")
(def center (list :width "935px" :margin-left "auto" :margin-right "auto"))
(def content-box (list :border-color acolor
                       :border-style "solid"
                       :border-width "1px"
                       :padding-left mrg
                       :padding-right mrg
                       :padding-top mrg
                       :padding-bottom mrg
                       :background-color box-bcolor
                       :box-shadow "0px 0px 5px #002454"))

(def sidebar
   [:#sidebar ;; the general sidebar div
    :width "182px"
    :float "right"
    :margin-left mrg
    [:#links-box ;; the box with all the links
     content-box
     :margin-top mrg
     :padding "20px"]
    [:a#tag-link:visited
     :color acolor]
    [:#about-box ;; the box with the about info
     content-box
     :margin-top "17px"
     :font-style "italic"
     :font-size "12px"
     :color "#AAA"
     :padding "20px"]
    [:.sidebar-item ;; each individual link
     :font-size "18px"
     :padding "3px"
     :display "inline"]
    [:a.sidebar-item:visited
     :color acolor]
    [:.tag-number
     :display "inline"]
    [:.sidebar-title ;; a title in the links box
     :font-size "17px"
     :text-indent "0"]])

(def post
   [:#posts ;; the box for all posts
    content-box
    :width "660px"
    :float "left"
    [:.post-header ;; the title of each post
     :font-size "30px"
     :font-weight "bold"
     :text-shadow "0px 1px 1px #6b4808"
     :margin-bottom mrg]
    [:.post-header:visited
     :color acolor]
    [:h2
     :font-size "18px"
     :color acolor]
    [:.post-header-hr ;; the hr under each title
     :border 0
     :width "100%"
     :height "1px"
     :color acolor
     :background-color acolor
     :margin-top mrg
     :margin-bottom mrg]
    [:.post-body ;; the actual text of the post
     :font-size "14px"
     :font-weight "normal"
     :margin-top "10px"]
    [:.post-info ;; the tags. date, and author info
     :font-size "10px"
     :color "#888"
     :font-style "normal"
     :text-indent "10px"
     :display "inline"]
    [:.between-post-hr ;; the hr that goes between posts
     :border 0
     :width "80%"
     :height "3px"
     :color global-bcolor
     :margin-top "40px"
     :margin-bottom "40px"
     :background-color global-bcolor]
    [:.tag-link
     :font-style "italic"]
    [:#tag-link:visited
     :color acolor]
    [:#info-sep
     :font-style "normal"
     :color "#AAA"
     :font-size "14px"
     :display "inline"]
    [:pre
     :overflow "auto"
     :color "#A8A8A8"
     :padding "10px"
     :background-color global-bcolor
     ]])

(def footerdiv
  [:#footer ;; the footer div
   :color "#8B8B8B"
   :margin-top mrg
   :margin-bottom mrg
   :float "left"
   :text-align "center"
   :width "780px"
   [:a.footer-link ;; the links in the footer
    :color "#9D9D9D"
    :text-decoration "none"]
   [:a.footer-link:hover
    :text-decoration "underline"]])

(def global
  [:html
   :margin-top mrg
   :padding 0
   :background-color global-bcolor
   :color fcolor
   :font-family "Helvetica,Arial,Sans-serif"
   :font-size "14px"
   :line-height "1.7"
   [:body ;; the body of the site
    :margin 0
    :padding 0]
   [:#content ;; the posts-box, sidebar, and logo all in one div
    center
    :height "100%"]
   [:a:link ;; the global link rules
    :text-decoration "none"
    :color acolor]
   [:a:hover
    :color acolor
    :text-decoration "underline"]
   [:a:active
    :color lightacolor]
   [:a:visited
    :color lightacolor]
   [:#tag-header ;; the header seen when looking at a tags page
    content-box
    :float "left"
    :margin-bottom mrg
    :font-size "24px"]])

  (defn save-blog-css [file]
    (save-css file global sidebar post footerdiv))