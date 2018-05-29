package com.cst.service.util;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


public class Formula {
	
	private static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
	
	
	public static Double arithmetic(String expression) {
		
		try {
			
	       return (Double)jse.eval(expression);
	    } catch (Exception t) {  
	    } 
		
		return null;
		
	}
	

}
