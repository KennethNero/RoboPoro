package com.kbn1798.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/***
 * Basic configuration file, snakeyaml parses 'config.yml' into these values.
 * @author kener
 *
 */
public final class Configuration { 
	private HashMap<String, String> poroPics;
	private ArrayList<String> poroIntros;
	
	public Set<String> getPoroNames(){
		return poroPics.keySet();
	}
    
    public HashMap<String, String> getPoroPics() {
    	return poroPics;
    }
    public ArrayList<String> getPoroIntros() {
    	return poroIntros;
    }
    
    public void setPoroIntros(ArrayList<String> s) {
    	this.poroIntros=s;
    }
    public void setPoroPics(HashMap<String, String> s) {
    	this.poroPics=s;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Poropics: \n");
    	sb.append(poroPics.toString()+"\n");
    	sb.append("PoroIntros: \n");
    	sb.append(poroIntros.toString());
    	return sb.toString();
    	
    }
}