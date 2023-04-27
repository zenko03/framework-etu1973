/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ETU1973.framework.servlet;

import ETU1973.framework.Mapping;
import ETU1973.framework.Modelview;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aris
 */
public class FrontServlet extends HttpServlet {
    Map<String, Mapping> mappingUrls = new HashMap<>();
    @Override
    public void init() throws ServletException {
        // Initializing all of the class routes
        mappingUrls = Initmapping.getAllControllerURLMethods();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String currentURL = request.getRequestURI().replace(request.getContextPath(), "");    
        //response.getWriter().print(currentURL);
         response.getWriter().print(mappingUrls);
         response.getWriter().print(currentURL);
       if(mappingUrls.containsKey(currentURL)){
           
           Mapping mapping =  mappingUrls.get(currentURL);
           mapping.getClassName();
           System.out.print("zavatra"+mappingUrls.size());
         
           try {
               
               Object object = Class.forName(mapping.getClassName()).getConstructor().newInstance();
               
              // response.getWriter().print("fdgfsdbg");
             
              
                  //recupere les attributs de la classe
                        Field[] field = object.getClass().getDeclaredFields();

                        //les transformer en tableau de string pour la comparaison
                        String[] attributs = new String[field.length];
                        for(int j=0;j<field.length;j++)
                        {
                            attributs[j] = field[j].getName();
                        }

                        // Parcourir tous les paramètres et les valeurs du formulaire
                        Enumeration<String> paramNames = request.getParameterNames();
                        while (paramNames.hasMoreElements()) {
                            String paramName = paramNames.nextElement();

                            //Verifier si le parametre fait partie des attributs de la classe 
                            for(int j=0;j<attributs.length;j++)
                            {
                                 response.getWriter().print(currentURL);
                                if(attributs[j].equals(paramName))
                                {
                                    String[] paramValues = request.getParameterValues(paramName);
                                    Method method= object.getClass().getMethod("set"+attributs[j], field[j].getType());
                                    Object paramValue = convertParamValue(paramValues[0], field[j].getType());
                                    method.invoke(object,paramValue);
                                }
                                
                            }
                        }
                         Modelview modelview = (Modelview) object.getClass().getMethod(mapping.getMethod()).invoke(object);
                response.getWriter().print(modelview.getData());
              if(modelview.getData() != null){
                 
                  for(Map.Entry<String,Object> map : modelview.getData().entrySet()){
                      request.setAttribute(map.getKey(), map.getValue());
                  }
              } 
             
              RequestDispatcher dispat = request.getRequestDispatcher(modelview.getView());
                dispat.forward(request,response);
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
           } catch (InstantiationException ex) {
               Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
           } catch (IllegalAccessException ex) {
               Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
           } catch (NoSuchMethodException ex) {
               Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
           } catch (SecurityException ex) {
               Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
           } catch (IllegalArgumentException ex) {
               Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
           } catch (InvocationTargetException ex) {
               Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }
    private Object convertParamValue(String paramValue, Class<?> paramType) {
    if (paramType == String.class) {
        return paramValue;
    } else if (paramType == int.class || paramType == Integer.class) {
        return Integer.parseInt(paramValue);
    } else if (paramType == boolean.class || paramType == Boolean.class) {
        return Boolean.parseBoolean(paramValue);
    }else if (paramType == double.class || paramType == Double.class) {
        return Double.parseDouble(paramValue);
    }
    return null; 
}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // </editor-fold>

}
