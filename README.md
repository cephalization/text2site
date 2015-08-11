# text2site
A tool for formatting plaintext into HTML markup. Used on DEABnet! <br>
To adjust to your website, copy all HTML that comes before the text into initial.html, <br>
and everything after into final.html! <br>
When creating a DEABnet post, remember to edit initial.html with the appropriate blog title!

##REQUIREMENTS -
* Latest version of Java and JDK (if compiling)
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
* cd into text2site directory
* javac -g Text2SiteMain.java FileMaker.java
* mkdir tex2site
* mv *.class tex2site/
* jar cfm text2site.jar manifest.txt text2site/*.class

##TO COMPILE/WINDOWS##
######################
* Open CMD with elevated privileges
* Make sure the directory containing the latest jdk is set in path
* cd into text2site directory
* javac -g Text2SiteMain.java FileMaker.java
* mkdir tex2site
* move *.class tex2site/
* jar cfm text2site.jar manifest.txt text2site/*.class
* Alternatively, run compile.bat
