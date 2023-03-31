copy -R "D:/gitprojet/framework-etu1973/framework/build/web/WEB-INF/classes" "D:/gitprojet/framework-etu1973" jar
cd jar/classes
jar cf ../../framework.jar *
cd ../../
copy "D:\gitprojet\framework-etu1973\framework.jar" "D:/gitprojet/framework-etu1973/Test/build/web/WEB-INF/lib"
copy -R "D:\gitprojet\framework-etu1973\Test\build\web" war
cd war/web
jar cf ../../framework.war *
copy ../../framework.war "C:/Program Files/Apache Software Foundation/Tomcat 10.0_Tomcat10.0.20/webapps"





