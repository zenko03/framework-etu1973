@echo off 

javac framework\*.java -d classes

set java_class = D:\gitprojet\framework-etu1973\framework\build\web\WEB-INF\classes

set jarfile = framework.jar

jar -cvf "D:\gitprojet\framework-etu1973\test\src\WEB-INF\lib\framework.jar" -C "D:\gitprojet\framework-etu1973\framework\build\web\WEB-INF\classes" .

jar -cvf framework.war -C "D:\gitprojet\framework-etu1973\Test" .

copy "D:\gitprojet\framework-etu1973\Test" "C:\Program Files\Apache Software Foundation\Tomcat 10.0\webapps" 
