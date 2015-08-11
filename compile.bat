@echo off
cmd /c set path=%path%;"C:\Program Files\Java\jdk1.8.0_51\bin\;"
javac -g Text2SiteMain.java FileMaker.java
mkdir text2site
move *.class text2site/
jar cfm text2site.jar manifest.txt text2site/*.class
rmdir /s /q text2site
