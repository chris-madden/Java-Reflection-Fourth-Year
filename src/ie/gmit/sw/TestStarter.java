package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class TestStarter {

	public static void main(String[] args) throws FileNotFoundException, IOException 
	{
		
		// Need validation in case file does not exist
				
		JarInputStream in  = new JarInputStream(new FileInputStream(new File("testJar.jar"))); 
	
		JarEntry next = in.getNextJarEntry(); 
		
		while (next != null) 
		{ 
			
			if (next.getName().endsWith(".class")) 
			{
				
				String name = next.getName().replaceAll("/", "\\.");
				
				name = name.replaceAll(".class", "");
				
				if (!name.contains("$")) name.substring(0, name.length() - ".class".length()); 
				
				System.out.println(name);
				
			}// End if 
			
			next = in.getNextJarEntry();
		
		}// End while 
		
	}// End main

}// End 
