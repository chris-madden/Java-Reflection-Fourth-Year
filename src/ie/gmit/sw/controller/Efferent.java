package ie.gmit.sw.controller;

import ie.gmit.sw.controller.ClassSet;

/**
 * 
 * This class is used to find efferent couplings in each class in the jar file. The class implements the 
 * interface Calculator so it has to fulfill its contract of calculating
 *
 */
public class Efferent implements Calculator 
{
	
	private ClassSet classSetDependencies;
	private ClassSet classSetClasses;
	private Class<?> cls;
	private ClassHandler ch = new ClassHandler();
	
	/**
	 *  This constructor is used to pass over and initialize the list of classes from jar file,
	 *  it also initializes a Class. The calculate method is also called.
	 * 
	 * @param class1 A particular class passed over to be initialized
	 * @param classSet A list of the classes from the jar file
	 */
	public Efferent(Class<?> class1, ClassSet classSet) 
	{
		
		this.cls = class1;
		this.classSetClasses = classSet;
		
		calculate();
		
	}
	
	/**
	 * 
	 * This method is used to calculate the efferent couplings in each class.
	 * It calls classes which uses the reflection api to find out the contents of the class
	 * 
	 */
	public void calculate()
	{
		
		this.classSetDependencies = new ClassSet();
		
		ClassSet inFace = ch.getInterface(this.cls);
		
		if(inFace.size() > 0){
			
			for (int i = 0; i < inFace.size(); i++) {
				
				this.classSetDependencies.add(inFace.get(i));
				
			}
			
		}// End if
		
		// -----------------------------------------------------------------
		// 						Get superclass
		Class<?> superCls = ch.getSuperClass(this.cls, this.classSetClasses);
		
		if(superCls != null)
		{
			
			this.classSetDependencies.add(superCls);
			
		}
		// -----------------------------------------------------------------
		
		// -----------------------------------------------------------------
		//						Get Fields
		ClassSet jarSetFields = ch.getFields(this.cls, this.classSetClasses);
		filter(jarSetFields);
		// -----------------------------------------------------------------
		
		// ---------------------------------------------------------------------------
		// 						Get constructor parameters
		ClassSet jarsetConParam = ch.getConstructorParams(this.cls, this.classSetClasses);
		filter(jarsetConParam);
		// ---------------------------------------------------------------------------
		
		// --------------------------------------------------------------------
		// 						Get method parameters
		ClassSet methodParams = ch.getMethodParams(this.cls, this.classSetClasses);
		filter(methodParams);
		// --------------------------------------------------------------------
		
		// --------------------------------------------------------------------
		// 						Get method return type
		ClassSet methodReturn = ch.getMethodReturn(this.cls, this.classSetClasses);
		filter(methodReturn);
		// --------------------------------------------------------------------

	}// End calculate

	/**
	 * This method is used to retrieve a single class
	 * 
	 * @return A class type is returned
	 */
	public Class<?> getCls() 
	{
		return cls;
	}

	/**
	 * This method finds if a class is contained in the list and if it isn't then 
	 * it's added to a list containing dependencies
	 * 
	 * @param classSet A list of classes from the jar 
	 */
	private void filter(ClassSet classSet)
	{
		
		if(classSet.size() > 0)
		{
			
			for (int i = 0; i < classSet.size(); i++) 
			{
				
				if(!classSetDependencies.contains(classSet.get(i)))
				{
					
					this.classSetDependencies.add(classSet.get(i));
					
				}
				
			}
		}
		
	}
	
	/**
	 * This method is used to get a list of classes that all have dependencies
	 * 
	 * @return A type of ClassSet is returned which contains a list
	 */
	public ClassSet getJarSetDependencies() 
	{
		return classSetDependencies;
	}

	/**
	 * This method initializes a ClassSet list that belongs to this class
	 * 
	 * @param classSetDependencies A ClassSet type is passed in
	 */
	public void setJarSetDependencies(ClassSet classSetDependencies)
	{
		this.classSetDependencies = classSetDependencies;
	}
	
	/**
	 * This method is used to return the number of dependencies
	 * 
	 * @return  A double is returned that represents the number of dependencies a class has
	 */
	public double getResult() 
	{
		return (double)classSetDependencies.size();
	}
		
}// End class Efferent
