/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizador;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author dark_
 */
public class Tabla {
    private Hashtable<String,String> DataType = new Hashtable<>();
    private ArrayList<String> Type = new ArrayList<>();
    
    int index = 0;
    
    public boolean existKey(String key){
        return DataType.containsKey(key);
    }
    
    public String gDataType(String k){
        return DataType.get(k);
    }
    
    public void setColum(String colum,String type){
        DataType.put(colum,type);
        Type.add(type);
    }
    
    public void upind(){
        index++;
    }
    
    public String gType (int ind){
        return Type.get(ind);
    }
    
    public int gsType(){
        return Type.size();
    }
}
