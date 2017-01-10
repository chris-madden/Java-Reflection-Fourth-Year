package ie.gmit.sw.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * 
 *  This class provides reflection API functionality. It consists of methods that retrieves information about classes.
 *
 */
public class ClassHandler
{
	
	/**
	 * This method checks if the class implements any interfaces
	 * 
	 * @param cls A class is passed to the method
	 * @return A list of interfaces is returned
	 */
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

	/**
	 * This method checks if a class has a superclass that isn't the class Object
	 * 
	 * @param cls A class is passed to the method
	 * @param classSetClasses A ClassSet type is passed to the method
	 * @return If a superclass is present then return that superclass. Otherwise return null.
	 */
	// Get super class
	public Class<?> getSuperClass(Class<?> cls, ClassSet classSetClasses) 
	{
		
		if(!cls.isInterface())
		{
			
			Class<?> class1 = cls.getSuperclass();
			
			for (int i = 0; i < classSetClasses.size(); i++) 
			{
				if(class1.getName().equals(classSetClasses.get(i).getName()))
				{
					
					return class1;
					
				}
				
			}// End for
			
		}// End if
		
		return null;
		
	}// End getSuperClass

	/**
	 * This method checks what fields the class has
	 * 
	 * @param cls A class is passed to the method
	 * @param classSetClasses A ClassSet type is passed to the method
	 * @return A ClassSet type is returned
	 */
	// Get fields
	public ClassSet getFields(Class<?> cls, ClassSet classSetClasses)
	{
		ClassSet classSetFields = new ClassSet();
		
		Field[] fields = cls.getDeclaredFields();
		
		for(Field f : fields)
		{
			
			Type type = f.getType();
			
			for (int i = 0; i < classSetClasses.size(); i++)
			{
				
				if(type.getTypeName().equals(classSetClasses.get(i).getName()))
				{
					
					Class<?> cField = classSetClasses.get(i);
					
					classSetFields.add(cField);
					
				}
				
			}// End inner for
			
		}// End outer for
		
		return classSetFields;
		
	}// End getFields

	/**
	 * This method checks what parameters the constructor has
	 * 
	 * @param cls A class is passed to the method
	 * @param classSetClasses ClassSet type is passed to the method
	 * @return A ClassSet type is returned
	 */
	// Get parameters from constructors
	public ClassSet getConstructorParams(Class<?> cls, ClassSet classSetClasses)
	{
		
		ClassSet classSetParams = new ClassSet();
		
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
						
						if(classSetClasses.contains(item))
						{
							
							classSetParams.add(item);
							
						}
						
					}// End inner for
					
				}
				
			}// End outer for
			
		}
		
		return classSetParams;
		
	}// End getConstructorParams
	
	/**
	 * This method checks what parameters the class methods have
	 * 
	 * @param cls A class is passed to the method
	 * @param classSetClasses ClassSet type is passed to the method
	 * @return A ClassSet type is returned
	 */
	// Get parameters from methods
	public ClassSet getMethodParams(Class<?> cls, ClassSet classSetClasses) 
	{
		
		ClassSet classSetParams = new ClassSet();
		
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
						
						if(classSetClasses.contains(item))
						{
							
							classSetParams.add(item);
							
						}
						
					}// End inner for
					
				}
				
			}// End outer for
			
		}
		
		return classSetParams;
		
	}// End getMethodParams

	/**
	 * This method checks the return types of the class methods
	 * 
	 * @param cls A class is passed to the method
	 * @param classSetClasses ClassSet type is passed to the method
	 * @return A ClassSet type is returned
	 */
	// Get return type of methods
	public ClassSet getMethodReturn(Class<?> cls, ClassSet classSetClasses)
	{
		
		ClassSet classSetReturn = new ClassSet();
		
		Method[] method = cls.getDeclaredMethods();
		
		if(method.length > 0)
		{
			
			for (Method m : method)
			{
				
				Class<?> class1 = m.getReturnType();
				
				if(classSetClasses.contains(class1))
				{
					
					classSetReturn.add(class1);
					
				}
				
			}// End for
			
		}// End if
		
		return classSetReturn;
		
	}// End getMethodReturn

}// End class handler
