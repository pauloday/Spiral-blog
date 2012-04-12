Spiral Version 1
---
This is a very minimal blog engine. It generates a static html site
from a configuration file.

Configuration
---
The configuration file is a clojure file that must define certain
variables. The example configuration is in the directory
example-blog, which also contains a file structure for a simple
blog. Example-config.clj will generate a blog out of all of the
markdown formatted posts (that end with ".md") in "post-files". The
only thing that must be changed is the \*out-folder\* variable in
example-config.clj, and it must be a absolute path (i.e it must start
with "/" not "~"). All of the other paths are reletive to
\*out-folder\*. More information on the specific config variables is
in the esample config.

Post Formmating
---
A post is a markdown file, with a little extra. The first line of the
post will be either the title if it is ready to be posted but has not
yet, the string "$D" if it is not ready to be posted and should be
ignored, or a date string starting with a "$" if it has been posted on
said date. If the first line is not the title, the second line is. The
line after the title is the tags list. It is a whitespace and/or comma
seperated list of tags for the post to be filed under. All of the
remaining lines are the markdown formatted body fo the post. An
example for a post posted on april 3, 2012:
    
    $Apr032012
    Title
    Tag1 Tag2
    This is the body

License
---

See the LICENSE file.
