package ie.gmit.sw.controller;

import ie.gmit.sw.controller.ClassSet;

public class Efferent implements Calculator 
{
	
	private ClassSet classSetDependencies;
	private ClassSet classSetClasses;
	private Class<?> cls;
	private ClassHandler ch = new ClassHandler();
	
	public Efferent(Class<?> class1, ClassSet classSet) 
	{
		
		this.cls = class1;
		this.classSetClasses = classSet;
		
		calculate();
		
	}
	
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

	public Class<?> getCls() 
	{
		return cls;
	}

	private void filter(ClassSet jarset)
	{
		
		if(jarset.size() > 0)
		{
			
			for (int i = 0; i < jarset.size(); i++) 
			{
				
				if(!classSetDependencies.contains(jarset.get(i)))
				{
					
					this.classSetDependencies.add(jarset.get(i));
					
				}
				
			}
		}
		
	}
	
	public ClassSet getJarSetDependencies() 
	{
		return classSetDependencies;
	}

	public void setJarSetDependencies(ClassSet jarSetDependencies)
	{
		this.classSetDependencies = jarSetDependencies;
	}
	
	public double getResult() 
	{
		return (double)classSetDependencies.size();
	}
		
}// End class Efferent
