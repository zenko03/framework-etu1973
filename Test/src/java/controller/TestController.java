package controller;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import ETU1973.framework.Modelview;
import ETU1973.framework.servlet.annotations.Route;
import ETU1973.framework.servlet.annotations.Controller;
import java.util.ArrayList;
import java.util.Vector;

@Controller
public class TestController{
    private String nom;
    private String prenom;
    private int age;
    private double mesure;
    
      public String getnom()
    {
        return this.nom;
    }
    public String getprenom()
    {
        return this.prenom;
    }
    public int getage()
    {
        return this.age;
    }
    public double getmesure()
    {
        return this.mesure;
    }
    public void setnom(String nom)
    {
        this.nom=nom;
    }
    public void setprenom(String prenom)
    {
        this.prenom=prenom;
    }
    public void setage(int age)
    {
        this.age=age;
    }
    public void setmesure(double mesure)
    {
        this.mesure=mesure;
    }

    public TestController(String nom, String prenom, int age, double mesure) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.mesure = mesure;
    }
    public TestController(){
    }

    
    @Route( url = "/test" )
    public Modelview index() {
        Modelview modelview = new Modelview();
        Vector<String> test = new Vector<String>(); 
        test.add("coucou");
        modelview.addItem("test",test);
        modelview.setView("huhu.jsp");
        return modelview;
    }
    
       @Route(url="/loadform")
    public Modelview load_form()
    {
        Modelview mv=new Modelview();
        mv.setView("Formlaire.jsp");
        return mv;
    }
    @Route(url="/formulaire")
    public Modelview getcoordonnees()
    {
        Modelview mv=new Modelview();
        mv.setView("Valider.jsp");
        ArrayList<TestController> olona=new ArrayList<TestController>();
        TestController user=new TestController(this.getnom(),this.getprenom(),this.getage(),this.getmesure());
        olona.add(user);
        mv.addItem("Liste_personne",olona);
        return mv;
    }
}
