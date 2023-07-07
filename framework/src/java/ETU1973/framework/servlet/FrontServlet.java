/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ETU1973.framework.servlet;

import ETU1973.framework.FileUpload;
import ETU1973.framework.Mapping;
import ETU1973.framework.Modelview;
import ETU1973.framework.servlet.annotations.Scope;
import com.sun.jdi.InvocationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
@MultipartConfig
public class FrontServlet extends HttpServlet {

    Map<String, Mapping> mappingUrls = new HashMap<>();
    HashMap<String, Object> hashmap = new HashMap<>();

    public Map<String, Mapping> getMappingUrls() {
        return mappingUrls;
    }

    public void setMappingUrls(Map<String, Mapping> mappingUrls) {
        this.mappingUrls = mappingUrls;
    }

    public HashMap<String, Object> getHashmap() {
        return hashmap;
    }

    public void setHashmap(HashMap<String, Object> hashmap) {
        this.hashmap = hashmap;
    }
    
    
    @Override
    public void init() throws ServletException {
        // Initializing all of the class routes
        mappingUrls = Initmapping.getAllControllerURLMethods();
        hashmap = Initmapping.GetAllSingleton();
        
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String currentURL = request.getRequestURI().replace(request.getContextPath(), "");
        //out.print(currentURL);
        PrintWriter out = response.getWriter();
        out.print(mappingUrls);
        out.print(currentURL);
        if (mappingUrls.containsKey(currentURL)) {
            Mapping mapping = mappingUrls.get(currentURL);
            mapping.getClassName();
            System.out.print("zavatra" + mappingUrls.size());
            try {
                Class classy= Class.forName(mapping.getClassName());
                Object objct = Class.forName(mapping.getClassName()).getConstructor().newInstance();
                Object object = this.GetInClassInstance(classy.getName(), classy);
              // out.print("fdgfsdbg");
                 Method[] methods = object.getClass().getDeclaredMethods();
                        Method fonction = null;
                        
                        for (Method mt : methods) {
                            if (mt.getName().equals(mapping.getMethod())) {
                                fonction = mt;
                                break;
                            }
                        }
                //recupere les attributs de la classe
                Field[] field = object.getClass().getDeclaredFields();
                Parameter[] param=fonction.getParameters();
                                        
                //les transformer en tableau de string pour la comparaison
                String[] attributs = new String[field.length];
                for (int j = 0; j < field.length; j++) {
                    attributs[j] = field[j].getName();
                }
                // Parcourir tous les paramètres et les valeurs du formulaire
                ArrayList<Object> parameter=new ArrayList<>();
                Enumeration<String> paramN = request.getParameterNames();
                ArrayList<String> paramNames = Collections.list(paramN);
                out.println(paramNames);
//                for (int i = 0 ; i < paramNames.size() ; i++) {
//                    String paramName = paramNames.get(i);
                  //Verifier si le parametre fait partie des attributs de la classe 
                    for (int j = 0; j < attributs.length; j++) {
                        
                        out.print(currentURL);
                         for (int i = 0 ; i < paramNames.size() ; i++) {
                             String paramName = paramNames.get(i);
                             out.println(paramNames);
                              if (attributs[j].equals(paramName)) {
                            String[] paramValues = request.getParameterValues(paramName);
                            Method method = object.getClass().getMethod("set" + attributs[j], field[j].getType());
                            Object paramValue = convertParamValue(paramValues[0], field[j].getType());
                            method.invoke(object, paramValue);
                        }
                         }
//                        if (attributs[j].equals(paramName)) {
//                            String[] paramValues = request.getParameterValues(paramName);
//                            Method method = object.getClass().getMethod("set" + attributs[j], field[j].getType());
//                            Object paramValue = convertParamValue(paramValues[0], field[j].getType());
//                            method.invoke(object, paramValue);
//                        }
                    }
                     for(int l=0;l<param.length;l++){
                                out.println(param[l].getName());
                                for (int i = 0 ; i < paramNames.size() ; i++) {
                                    String paramName = paramNames.get(i);
                                    if(param[l].getName().trim().equals(paramName.trim()))
                                {
                                    String paramValues = request.getParameter(paramName.trim());
                                    Object paramValue = convertParamValue(paramValues, param[l].getType());
                                    parameter.add(paramValue);
                                }
                                }
//                                if(param[l].getName().trim().equals(paramName.trim()))
//                                {
//                                    String paramValues = request.getParameter(paramName.trim());
//                                    Object paramValue = convertParamValue(paramValues, param[l].getType());
//                                    parameter.add(paramValue);
//                                }
                            }
//                }

                try {
                    Collection<Part> files = request.getParts();
                    for(Field fld : field){
                        if(fld.getType()== ETU1973.framework.FileUpload.class){
                            Method meth = object.getClass().getMethod("set" + fld, fld.getType());
                            Object obj = this.fileTraitement(files, fld);
                            meth.invoke(object,obj);
                        }
                    }
                } catch (ServletException | IOException e) {
                }
                    
                Modelview modelview = (Modelview) fonction.invoke(object , parameter.toArray(new Object[parameter.size()]));
                out.print(modelview.getData());
                if (modelview.getData() != null) {
                    for (Map.Entry<String, Object> map : modelview.getData().entrySet()) {
                        request.setAttribute(map.getKey(), map.getValue());
                    }
                }                
                RequestDispatcher dispat = request.getRequestDispatcher(modelview.getView());
                dispat.forward(request, response);
            } catch( Exception e ){
                e.printStackTrace(out);
            }
        }
    } 
  
    public void Reset(Object objet)throws IllegalAccessException, InvocationException, InvocationTargetException{
        Field[] fields = objet.getClass().getDeclaredFields();
        for(Field field : fields) {
            String fieldName = upperFirst(field.getName());
            Method methodSet = null;
            try {
                methodSet =objet.getClass().getMethod("set"+fieldName, field.getType());
            } catch (Exception e) {
                continue;
            }
            if(field.getType().equals(int.class)){
                methodSet.invoke(objet, 0);
            }
             if(field.getType().equals(double.class)){
                methodSet.invoke(objet, 0);
            }
              if(field.getType().equals(Object.class)){
                methodSet.invoke(objet, (Object)null);
            }
              if(field.getType().equals(float.class)){
                methodSet.invoke(objet, 0);
            } 
              
              

        }
    
    }
   
  
    ///////////////////////////////UPLOADFILE////////////////////////////
    
    private FileUpload fileTraitement(Collection<Part> files, Field field) {
        FileUpload file = new FileUpload();
        String name = field.getName();
        boolean exists = false;
        String filename = null;
        Part filepart = null;
        for (Part part : files) {
            if (part.getName().equals(name)) {
                filepart = part;
                exists = true;
                break;
            }
        }
        try (InputStream io = filepart.getInputStream()) {
            ByteArrayOutputStream buffers = new ByteArrayOutputStream();
            byte[] buffer = new byte[(int) filepart.getSize()];
            int read;
            while ((read = io.read(buffer, 0, buffer.length)) != -1) {
                buffers.write(buffer, 0, read);
            }
            file.setNom(this.getFileName(filepart));
            file.setBytes(buffers.toByteArray());
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] parts = contentDisposition.split(";");
        for (String partStr : parts) {
            if (partStr.trim().startsWith("filename"))
                return partStr.substring(partStr.indexOf('=') + 1).trim().replace("\"", "");
        }
        return null;
    }
   
    public Object GetInClassInstance(String className, Class<?> classe) throws IllegalAccessException, InstantiationException, InvocationTargetException, InvocationException{
        Object objet;
        if(this.getHashmap().containsKey(className)){
            Object obj = this.getHashmap().get(className);
            if(obj == null){
            obj = classe.newInstance();
            objet = obj;
            this.getHashmap().put(className, objet);
            }else{
                this.Reset(obj);
                objet=obj;
                
            }
        }else{
        objet = classe.newInstance();
        }
        return objet;
    }
    
    private Object convertParamValue(String paramValue, Class<?> paramType) {
        if (paramType == String.class) {
            return paramValue;
        } else if (paramType == int.class || paramType == Integer.class) {
            return Integer.parseInt(paramValue);
        } else if (paramType == boolean.class || paramType == Boolean.class) {
            return Boolean.parseBoolean(paramValue);
        } else if (paramType == double.class || paramType == Double.class) {
            return Double.parseDouble(paramValue);
        }
        return null;
    }
    public String upperFirst(String text){
        return text.substring(0,1).toUpperCase() + text.substring(1);
 
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
