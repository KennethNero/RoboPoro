package com.kbn1798.utils;

import java.util.ArrayList;

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
 
//    @Override
//    public String toString() {
//        return new StringBuilder()
//            .append( format( "Version: %s\n", version ) )
//            .append( format( "Released: %s\n", released ) )
//            .append( format( "Supported protocols: %s\n", protocols ) )
//            .append( format( "Users: %s\n", users ) )
//            .toString();
//    }
}