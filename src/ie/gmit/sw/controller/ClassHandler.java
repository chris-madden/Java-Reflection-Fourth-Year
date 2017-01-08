package ie.gmit.sw.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class ClassHandler
{
	
	// Get interfaces
	public ClassSet getInterface(Class<?> cls) 
	{
		
		ClassSet inFace = new ClassSet();
		
		Class<?> inFaceArray[] = cls.getInterfaces();
		
		for(Class<?> c : inFaceArray)
		{
			inFace.add(c);
		}
		
		return inFace;
		
	}// End getInterface

	// Get super class
	public Class<?> getSuperClass(Class<?> cls, ClassSet jarSetClasses) 
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
				
			}// End for
			
		}
		
		return null;
		
	}// End getSuperClass
	
	// Get fields
	public ClassSet getFields(Class<?> cls, ClassSet jarSetClasses)
	{
		ClassSet jarsetFields = new ClassSet();
		
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
				
			}// End inner for
			
		}// End outer for
		
		return jarsetFields;
		
	}// End getFields

	// Get parameters from constructors
	public ClassSet getConstructorParams(Class<?> cls, ClassSet jarSetClasses)
	{
		
		ClassSet jarsetParams = new ClassSet();
		
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
						
					}// End inner for
					
				}
				
			}// End outer for
			
		}
		
		return jarsetParams;
		
	}// End getConstructorParams
	
	// Get parameters from methods
	public ClassSet getMethodParams(Class<?> cls, ClassSet jarSetClasses) 
	{
		
		ClassSet jarsetParams = new ClassSet();
		
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
						
					}// End inner for
					
				}
				
			}// End outer for
			
		}
		
		return jarsetParams;
		
	}// End getMethodParams

	// Get return type of methods
	public ClassSet getMethodReturn(Class<?> cls, ClassSet jarSetClasses)
	{
		
		ClassSet jarsetReturn = new ClassSet();
		
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
				
			}// End for
			
		}
		
		return jarsetReturn;
		
	}// End getMethodReturn

}// End class handler
