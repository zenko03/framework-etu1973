/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ETU1973.framework;

import java.util.HashMap;

/**
 *
 * @author itu
 */
public class Modelview {
    String view;
    HashMap<String, Object> data;

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
    public void addItem(String string, Object obj){
        if(getData()==null){
            setData(new HashMap<String,Object>());
        }
        data.put(string, obj);
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
    
}
