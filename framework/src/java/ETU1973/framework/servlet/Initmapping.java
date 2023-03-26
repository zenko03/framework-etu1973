/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ETU1973.framework.servlet;


import ETU1973.framework.servlet.annotations.*;
import ETU1973.framework.Mapping;
import ETU1973.framework.GetAnnotation;
import ETU1973.framework.servlet.annotations.Route;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aris
 */
public class Initmapping {

    /**
     *
     * @return
     */
    public static Map<String, Mapping> getAllControllerURLMethods() {
        List<Class<?>> controllers = GetAnnotation.getClassesWithAnnotation(Controller.class);
        
        // Creating Hash Map containing the url to the controller method and the mapping class
        Map<String, Mapping> urlMapping = new HashMap<>();
        
        // Looping through all the classes annotated with the annotation controller
        for (Class<?> controller : controllers) {
            // Getting all the methods for each controller
            Method[] controllerMethods = controller.getMethods();
            
            for (Method method : controllerMethods) {
                // Creating and putting a Mapping and key in the urlMapping has map
                if (method.isAnnotationPresent(Route.class)) {
                    Mapping mapping = new Mapping();
                    
                    mapping.setClassName(controller.getName());
                    mapping.setMethod(method.getName());
                    
                    // Getting the url to the app route
                    Route appRoute = method.getAnnotation(Route.class);
                    String url = appRoute.url();
                    // Putting the mapping class in the dictionnary
                    urlMapping.put(url, mapping);
                }
            }
        }
        
        return urlMapping;
    }
}
