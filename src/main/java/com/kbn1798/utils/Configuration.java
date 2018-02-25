package com.kbn1798.utils;

import java.util.ArrayList;

/***
 * Basic configuration file, snakeyaml parses 'config.yml' into these values.
 * @author kener
 *
 */
public final class Configuration { 
	private ArrayList<String> poroPics;
	private ArrayList<String> poroIntros;
    
    public ArrayList<String> getPoroPics() {
    	return poroPics;
    }
    public ArrayList<String> getPoroIntros() {
    	return poroIntros;
    }
    
    public void setPoroIntros(ArrayList<String> s) {
    	this.poroIntros=s;
    }
    public void setPoroPics(ArrayList<String> s) {
    	this.poroPics=s;
    }
 
}