set libTomcat=C:\Program Files\Apache Software Foundation\Tomcat 8.5\lib\*
set pathTomcat=C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapps
mkdir temp
javac -cp "temp;%libtomcat%" -d temp E:\github\framework-etu1973\framework\src\java\ETU1973\framework\servlet\annotations\*.java
javac -cp "temp;%libtomcat%" -d temp E:\github\framework-etu1973\framework\src\java\ETU1973\framework\Mapping.java
javac -cp "temp;%libtomcat%" -d temp E:\github\framework-etu1973\framework\src\java\ETU1973\framework\Modelview.java
javac -cp "temp;%libtomcat%" -d temp E:\github\framework-etu1973\framework\src\java\ETU1973\framework\GetAnnotation.java
javac -cp "temp;%libtomcat%" -d temp E:\github\framework-etu1973\framework\src\java\ETU1973\framework\servlet\Initmapping.java
javac -cp "temp;%libtomcat%" -d temp E:\github\framework-etu1973\framework\src\java\ETU1973\framework\servlet\FrontServlet.java

cd E:\github\framework-etu1973\temp

jar cvf E:\github\framework-etu1973\Test\web\WEB-INF\lib\framework.jar .
cd../
rd /s /q temp
mkdir temp
xcopy E:\github\framework-etu1973\Test\web\* temp\ /s /e /y

set framework_jar="E:\github\framework-etu1973\temp\WEB-INF\lib\framework.jar"

javac -cp "E:\github\framework-etu1973\Test\web\WEB-INF\classes;%framework_jar%" -d E:\github\framework-etu1973\temp\WEB-INF\classes\ E:\github\framework-etu1973\Test\src\java\controller\*.java

jar cvf "%pathTomcat%\framework.war" -C temp .
rd /s /q temp

