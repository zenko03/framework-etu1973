jar -cf fw.jar -C .\Framework\build\web\WEB-INF\classes\ETU1973
jar -cvf test_fw.war -C .\Test\build\web\WEB-INF\
copy "D:\gitprojet\framework-etu1973\test_fw.war" "C:\Program Files\Apache_Software_Foundation\Tomcat 10.0_Tomcat10.0.20\webapps"
copy "D:\gitprojet\framework-etu1973\fw.jar" "D:\gitprojet\framework-etu1973\Test\build\web\WEB-INF\lib"
copy "D:\gitprojet\framework-etu1973\Test\build\web\test_fw"  "C:\Program Files\Apache_Software_Foundation\Tomcat 10.0_Tomcat10.0.20\webapps" /E