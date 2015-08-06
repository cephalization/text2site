# text2site
A tool for formatting plaintext into HTML markup. Used on DEABnet! <br>
To adjust to your website, copy all HTML that comes before the text into initial.html, <br>
and everything after into final.html! <br>
When creating a DEABnet post, remember to edit initial.html with the appropriate blog title!

##REQUIREMENTS -
* Latest version of Java
* initial.html in same directory as program
* final.html in same directory as program

##METHODS -
* Write/Build from file, navbar/head/open HTML
* Write/Build formatted blog post (only auto adds p tags for now)
* Write/Build from file, closing tags
* Read user input
* Format user input

##TO COMPILE/UNIX##
###################
* javac -g Text2SiteMain.java
* mkdir tex2site
* mv Text2SiteMain.class tex2site/Text2SiteMain.class
* jar cfm text2site.jar manifest.txt tex2site/Text2SiteMain.class

##TO COMPILE/WINDOWS##
######################
* I'll get here eventually...
