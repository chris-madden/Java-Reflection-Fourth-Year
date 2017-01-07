package ie.gmit.sw.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import ie.gmit.sw.controller.JarSet;
import ie.gmit.sw.launch.Runner;

public class ClassHandler
{
	
	private JarSet list;
	
	public JarSet getClassesFromJar(String nameOfJar) throws FileNotFoundException, IOException
	{
		
		   // List with classes from Jar file
		   this.list = new JarSet();
		   
		   JarInputStream in  = new JarInputStream(new FileInputStream(new File(nameOfJar))); 
			
		   JarEntry next = in.getNextJarEntry(); 
		   
		   while(next != null)
		   {
			   
			   if (next.getName().endsWith(".class")) 
				{
					
					String name = next.getName().replaceAll("/", "\\.");
					
					name = name.replaceAll(".class", "");
					
					if (!name.contains("$")) 
						name.substring(0, name.length() - ".class".length()); 
					
					 Class<?> queryClass;
					 
					try {
						
						queryClass = Class.forName(name);
						
						list.add(queryClass);
						 
						new Runner(queryClass);
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					
					// Store class name in a data structure
					//System.out.println(name);
					
				}// End if 
				
				next = in.getNextJarEntry();
			  
		   }
		
		   in.close();
		   
		   return list;
		   
	}// End getClassesFromJar

	public JarSet getInterface(Class<?> cls) 
	{
		
		JarSet inFace = new JarSet();
		
		Class<?> inFaceArray[] = cls.getInterfaces();
		
		for(Class<?> c : inFaceArray)
		{
			inFace.add(c);
		}
		
		return inFace;
	}

	public Class<?> getSuperClass(Class<?> cls, JarSet jarSetClasses) 
	{
		
		if(!cls.isInterface())
		{
			
			Class<?> class1 = cls.getSuperclass();
			
			for (int i = 0; i < jarSetClasses.size(); i++) 
			{
				if(class1.getName().equals(jarSetClasses.get(i).getName()))
				{
					
					return class1;
					
				}
			}
			
		}
		
		return null;
	}
	
	public JarSet getFields(Class<?> cls, JarSet jarSetClasses)
	{
		JarSet jarsetFields = new JarSet();
		
		Field[] fields = cls.getDeclaredFields();
		
		for(Field f : fields)
		{
			
			Type type = f.getType();
			
			for (int i = 0; i < jarSetClasses.size(); i++)
			{
				
				if(type.getTypeName().equals(jarSetClasses.get(i).getName()))
				{
					
					Class<?> cField = jarSetClasses.get(i);
					
					jarsetFields.add(cField);
					
				}
				
			}
			
		}
		
		return jarsetFields;
	}

	public JarSet getConstructorParams(Class<?> cls, JarSet jarSetClasses)
	{
		
		JarSet jarsetParams = new JarSet();
		
		Constructor<?>[] cons = cls.getDeclaredConstructors();
		
		if(cons.length > 0)
		{
			
			for (Constructor<?> c : cons)
			{
				
				Class<?>[] class1 = c.getParameterTypes();
				
				if(class1.length > 0)
				{
					
					for (Class<?> item : class1)
					{
						
						if(jarSetClasses.contains(item))
						{
							
							jarsetParams.add(item);
							
						}
						
					}
					
				}
				
			}
			
		}
		
		return jarsetParams;
	}
	
	public JarSet getMethodParams(Class<?> cls, JarSet jarSetClasses) 
	{
		
		JarSet jarsetParams = new JarSet();
		
		Method[] method = cls.getDeclaredMethods();
		
		if(method.length > 0)
		{
			
			for (Method m : method)
			{
				
				Class<?>[] class1 = m.getParameterTypes();
				
				if(class1.length > 0)
				{
					
					for (Class<?> item : class1)
					{
						
						if(jarSetClasses.contains(item))
						{
							
							jarsetParams.add(item);
							
						}
						
					}
					
				}
				
			}
			
		}
		
		return jarsetParams;
	}

	public JarSet getMethodReturn(Class<?> cls, JarSet jarSetClasses)
	{
		
		JarSet jarsetReturn = new JarSet();
		
		Method[] method = cls.getDeclaredMethods();
		
		if(method.length > 0)
		{
			
			for (Method m : method)
			{
				
				Class<?> class1 = m.getReturnType();
				
				if(jarSetClasses.contains(class1))
				{
					
					jarsetReturn.add(class1);
					
				}
				
			}
			
		}
		
		return jarsetReturn;
	}

}// End class handler
