package ie.gmit.sw.controller;

import java.util.List;

public class Afferent implements Calculator{
	
	private ClassSet classSetDependencies;
	private Class<?> cls;
	private List<Efferent> listCE; 

	public Afferent(Class<?> class1, List<Efferent> listCE)
	{
		
		this.cls = class1;
		this.listCE = listCE;
		
		calculate();
	}
	
	public void calculate()
	{
		
		this.classSetDependencies = new ClassSet();
		
		for (int i = 0; i < listCE.size(); i++)
		{
			ClassSet classSetD = listCE.get(i).getJarSetDependencies();
			
			if(classSetD.size() > 0)
			{
				
				for (int j = 0; j < classSetD.size(); j++)
				{
					
					if(cls.equals(classSetD.get(j)))
					{
						
						this.classSetDependencies.add(listCE.get(i).getCls());
						
					}
					
				}
				
			}
		}
		
	}

	public ClassSet getJarsetDependencies() 
	{
		return classSetDependencies;
	}

	public Class<?> getCls()
	{
		return this.cls;
	}
	
	public double getResult() 
	{
		return (double)this.classSetDependencies.size();
	}

}// End class SAfferent
