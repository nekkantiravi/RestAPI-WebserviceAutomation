package com.qa.Configurations;

public class ResourceHelper {
	
	public static String getResourcepath(String path) {
		
		String basepath=System.getProperty("user.dir");
		
		return basepath+path;
		
	}
	
	public static void main(String[] args) {
		
        String path=ResourceHelper.getResourcepath("/src/main/java/com/qa/data/users.json");
        System.out.println(path);
	}
	
	

}
