(ns blog.css
  (:use gaka.core))


(def color "#EA7D24")
(def lcolor "F79321")
(def lgrey "#888")
(def dgrey "#393939")
(def bg "#eee")
(def hpad "30px")
(def center (list :margin-left "auto" :margin-right "auto"))
(def content-width 700)
(def pad "30px")
(def info-pad 10)
(def info-width (- content-width (* info-pad 4)))
(def info-item-width (- (/ content-width 2) (* 4 info-pad) 1))


(defn header-def [h size under align color & rest]
  [(keyword h)
   :font-size size
   :text-align align
   :color color
   :padding 0
   :line-height 1])


(def global
  [:html
   :margin-top 0
   :padding 0
   :color fcolor
   :font-family "Helvetica,Arial,Sans-serif"
   :font-size "14px"
   :background-color bg

   [:img
    :display "block"
    center]

   [:body ;; the body of the site
    :margin 0
    :padding 0]

   [:#content
    :width content-width
    center]

   (header-def 'h1 "70px" false "center" color)
   (header-def 'h2 "18px" true "center" lgrey)
   (header-def 'h3 "25px" true "left" lgrey)
   (header-def 'h4 "30px" false "left" color)
   (header-def 'h5 "12px" false "left" lgrey)

   [:#author-title
    :padding-top pad
    :text-align "center"]

   [:#author-link
    :color lgrey
    :text-decoration "underline"]

   [:#author-link:hover
    :color lcolor]

   [:.tag-link:visited
    :color color]

   [:a
    :text-decoration "none"
    :color color]

   [:a:hover
    :text-decoration "underline"
    :color lcolor]

   [:a:active
    :color color]

   [:a:visited
    :color lcolor]])


(def info
  [:#info
   :width  info-width
   :margin "20px"
   :float "right"
   :line-height "2"
   :display "table"
   center

   [:.info-row
    :display "table-row"]

   [:.info-cell
    :width info-item-width
    :display "table-cell"]

   [:#about
    :padding info-pad]

   [:#links
    :border-left (str "1px " "dotted " lgrey)
    :padding info-pad

    [:#link-desc
     :display "inline"]]])


(def posts-list
  [:#posts
   :width content-width
   :margin "10px"
   :float "left"
   center

   [:#post-list-title
    :display "inline"
    :margin "0px 50px 0px 50px"
    :line-height "2.8"]

   [:.post-listing
    :float "left"
    :width "100%"]

   [:#date
    :display "inline"
    :color lgrey
    :float "left"
    :font-family "Andale Mono, monospace"

    [:#month
     :font-size "18px"
     :line-height "20px"]

    [:#day
     :font-size "30px"
     :color dgrey
     :line-height "20px"]

    [:#year
     :font-size "14px"
     :line-height "16px"]]])


(def post
  [:#post
   :width content-width
   :line-height "1.9"])


(def footer
  [:#footer
   :text-align "center"
   :color lgrey
   :padding pad
   :float "left"
   :width "100%"

   [:a:visited
    :color color]])

(defn save-blog-css [file]
  (save-css file global info posts-list post footer))