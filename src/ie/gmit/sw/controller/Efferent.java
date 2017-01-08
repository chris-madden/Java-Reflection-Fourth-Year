package ie.gmit.sw.controller;

import ie.gmit.sw.controller.ClassSet;

public class Efferent 
{
	
	private ClassSet jarSetDependencies;
	private ClassSet jarSetClasses;
	private Class<?> cls;
	private ClassHandler ch = new ClassHandler();
	
	public Efferent(Class<?> class1, ClassSet jarSet) 
	{
		
		this.cls = class1;
		this.jarSetClasses = jarSet;
		
		calculate();
		
	}
	
	public void calculate()
	{
		
		this.jarSetDependencies = new ClassSet();
		
		ClassSet inFace = ch.getInterface(this.cls);
		
		if(inFace.size() > 0){
			
			for (int i = 0; i < inFace.size(); i++) {
				
				this.jarSetDependencies.add(inFace.get(i));
				
			}
			
		}// End if
		
		// -----------------------------------------------------------------
		// 						Get superclass
		Class<?> superCls = ch.getSuperClass(this.cls, this.jarSetClasses);
		
		if(superCls != null)
		{
			
			this.jarSetDependencies.add(superCls);
			
		}
		// -----------------------------------------------------------------
		
		// -----------------------------------------------------------------
		//						Get Fields
		ClassSet jarSetFields = ch.getFields(this.cls, this.jarSetClasses);
		filter(jarSetFields);
		// -----------------------------------------------------------------
		
		// ---------------------------------------------------------------------------
		// 						Get constructor parameters
		ClassSet jarsetConParam = ch.getConstructorParams(this.cls, this.jarSetClasses);
		filter(jarsetConParam);
		// ---------------------------------------------------------------------------
		
		// --------------------------------------------------------------------
		// 						Get method parameters
		ClassSet methodParams = ch.getMethodParams(this.cls, this.jarSetClasses);
		filter(methodParams);
		// --------------------------------------------------------------------
		
		// --------------------------------------------------------------------
		// 						Get method return type
		ClassSet methodReturn = ch.getMethodReturn(this.cls, this.jarSetClasses);
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
				
				if(!jarSetDependencies.contains(jarset.get(i)))
				{
					
					this.jarSetDependencies.add(jarset.get(i));
					
				}
				
			}
		}
		
	}
	
	public ClassSet getJarSetDependencies() 
	{
		return jarSetDependencies;
	}

	public void setJarSetDependencies(ClassSet jarSetDependencies)
	{
		this.jarSetDependencies = jarSetDependencies;
	}
	
	public double getResult() 
	{
		return (double)jarSetDependencies.size();
	}
		
}// End class Efferent
