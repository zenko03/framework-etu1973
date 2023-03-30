/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ETU1973.framework.app.controllers;


import ETU1973.framework.Modelview;
import ETU1973.framework.servlet.annotations.Route;
import ETU1973.framework.servlet.annotations.Controller;

@Controller
public class TestController{
    @Route(url="/test/index")
    public Modelview index() {
        Modelview modelview = new Modelview();
        modelview.setView("index.jsp");
        return modelview;
    }
}
