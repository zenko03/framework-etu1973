package controller;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import ETU1973.framework.Modelview;
import ETU1973.framework.servlet.annotations.Route;
import ETU1973.framework.servlet.annotations.Controller;
import java.util.Vector;

@Controller
public class TestController{
    @Route( url = "/test" )
    public Modelview index() {
        Modelview modelview = new Modelview();
        Vector<String> test = new Vector<String>(); 
        test.add("coucou");
        modelview.addItem("test",test);
        modelview.setView("huhu.jsp");
        return modelview;
    }
}
