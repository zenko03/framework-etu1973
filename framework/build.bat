
cd ./build/web/WEB-INF/classes
jar cf ./framework.jar ./ETU1973
mkdir "D:\gitprojet\framework-etu1973\Test\web\WEB-INF\lib\"
mkdir "D:\gitprojet\framework-etu1973\Test\build\web\WEB-INF\lib\"
copy "D:\gitprojet\framework-etu1973\framework\build\web\WEB-INF\classes\framework.jar" "D:\gitprojet\framework-etu1973\Test\web\WEB-INF\lib\"
copy "D:\gitprojet\framework-etu1973\framework\build\web\WEB-INF\classes\framework.jar" "D:\gitprojet\framework-etu1973\Test\build\web\WEB-INF\lib\"
cd "D:\gitprojet\framework-etu1973\Test\src\java"
javac -d "D:\gitprojet\framework-etu1973\Test\build\web\WEB-INF\classes" *.java
javac -d "D:\gitprojet\framework-etu1973\Test\build\web\WEB-INF\classes" controller\*.java
cd "D:\gitprojet\framework-etu1973\Test\build\web"

jar cf ./test-framework.war *
copy "D:\gitprojet\framework-etu1973\Test\build\web\test-framework.war" "C:\Program Files\Apache Software Foundation\Apache Tomcat 8.0.27\webapps"
