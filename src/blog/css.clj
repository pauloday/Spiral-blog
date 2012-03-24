(ns blog.css
  (:use gaka.core))

(def mrg 10)
(def pad 30)
(def box-bcolor "#E7E8F9")
(def global-bcolor "#595959")
(def fcolor "#393939")
(def acolor "#EA7D24")
(def lightacolor "#F79321")
(def orangetext (list :color acolor))
(def pboxwidth (- 660 (* 2 pad)))
(def sbarwidth (- 180 (* 2 pad)))
(def border 3)
(def center-width (+ pboxwidth mrg sbarwidth (* pad 4)))
(def center (list :width center-width :margin-left "auto" :margin-right "auto"))
(def shadow (list :box-shadow "0px 0px 20px #494949"))
(def box-bg (list :background-color box-bcolor))
(def content-box (list :border-color acolor
                       :padding pad
                       box-bg
                       ))

(def sidebar
  [:#sidebar ;; the general sidebar div
   content-box
   :width sbarwidth
   :float "right"

   [:a#tag-link:visited
    orangetext]

   [:#about-box ;; the box with the about info
    box-bg
    :font-style "italic"
    :font-size "12px"
    :color "#696969"
    :padding-top "20px"]

   [:.sidebar-item ;; each individual link
    :font-size "14px"
    :font-weight "bold"
    :padding "3px"
    :display "inline"]

   [:a.sidebar-item:visited
    orangetext]

   [:.tag-number
    :display "inline"]

   [:.sidebar-title ;; a title in the links box
    :font-size "17px"
    :text-indent "0"]])



(def post
  [:#posts ;; the box for all posts
   content-box
   :width pboxwidth
   :float "left"
   :position "reletive"

   [:.post-header ;; the title of each post
    :font-size "30px"
    :font-weight "bold"
    :margin-bottom mrg]

   [:.post-header:visited
    orangetext]

   [:h2
    :font-size "18px"
    orangetext]

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
    orangetext]

   [:#info-sep
    :font-style "normal"
    :color "#AAA"
    :font-size "14px"
    :display "inline"]])



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
   :margin-top 0
   :padding 0
   :background-color acolor
   :color fcolor
   :font-family "Helvetica,Arial,Sans-serif"
   :font-size "14px"
   :line-height "2"

   [:img
    :display "block"
    :margin-left "auto"
    :margin-right "auto"]

   [:body ;; the body of the site
    :margin 0
    :padding 0]

   [:#content ;; the posts-box, sidebar, and logo all in one div
    center]

   [:a:link ;; the global link rules
    :text-decoration "none"
    orangetext]

   [:a:hover
    orangetext
    :text-decoration "underline"]

   [:a:active
    :color lightacolor]

   [:a:visited
    :color lightacolor]

   [:#tag-header ;; the header seen when looking at a tags page
    :float "left"
    :padding pad
    :font-size "24px"]])



(defn save-blog-css [file]
  (save-css file global sidebar post footerdiv))