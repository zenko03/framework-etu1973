package controller;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import ETU1973.framework.Modelview;
import ETU1973.framework.servlet.annotations.Route;
import ETU1973.framework.servlet.annotations.Controller;
import ETU1973.framework.servlet.annotations.Scope;
import java.util.ArrayList;
import java.util.Vector;

@Controller
@Scope(singleton=true)
public class TestController{
    private int id;
    private String nom;
    private String prenom;
    private int age=0;
    private double mesure;

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }
    
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

    public TestController(int id,String nom, String prenom, int age, double mesure) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.mesure = mesure;
        this.id = id;
    }
    public TestController(){
    }

      @Route(url="/nomcomplet")
    public Modelview get_nomcomplet()
    {
        Modelview m=new Modelview();
        m.setView("View.jsp");
        ArrayList<TestController> olona=new ArrayList<TestController>();
        TestController jean=new TestController(1,"Rakoto","Jean",16,1.60);
        TestController robert=new TestController(2,"Randria","Robert",18,1.66);
        TestController jeanne=new TestController(3,"Andria","Jeanne",20,1.76);
        TestController marie=new TestController(4,"Rasoa","Marie",30,1.80);
        olona.add(jean);
        olona.add(robert);
        olona.add(jeanne);
        olona.add(marie);
        m.addItem("Liste_personne",olona);
        return m;
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
     @Route( url = "/upload" )
    public Modelview upload() {
        Modelview modelview = new Modelview();
        modelview.setView("Upload.jsp");
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
       // TestController user=new TestController(this.getid(),this.getnom(),this.getprenom(),this.getage(),this.getmesure());
       this.setage(this.getage()+1);
       olona.add(this);
        mv.addItem("Liste_personne",olona);
        return mv;
    }
        @Route(url="/detail")
    public Modelview voir_detail(int id)
    {
        Modelview mv=new Modelview();
        mv.setView("Details.jsp");
        ArrayList<TestController> olona=new ArrayList<TestController>();
        TestController[] pers=new TestController[4];
        TestController jean=new TestController(1,"Rakoto","Jean",16,1.60);
        pers[0]=jean;
        TestController robert=new TestController(2,"Randria","Robert",18,1.66);
        pers[1]=robert;
        TestController jeanne=new TestController(3,"Andria","Jeanne",20,1.76);
        pers[2]=jeanne;
        TestController marie=new TestController(4,"Rasoa","Marie",30,1.80);
        pers[3]=marie;
        olona.add(pers[id-1]);
        mv.addItem("Liste_personne",olona);
        return mv;
    }
   
}
