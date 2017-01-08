package ie.gmit.sw.controller;

import java.util.List;

public class Afferent {
	
	private ClassSet jarsetDependencies;
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
		
		this.jarsetDependencies = new ClassSet();
		
		for (int i = 0; i < listCE.size(); i++)
		{
			ClassSet jarSetD = listCE.get(i).getJarSetDependencies();
			
			if(jarSetD.size() > 0)
			{
				
				for (int j = 0; j < jarSetD.size(); j++)
				{
					
					if(cls.equals(jarSetD.get(j)))
					{
						
						this.jarsetDependencies.add(listCE.get(i).getCls());
						
					}
					
				}
				
			}
		}
		
	}

	public ClassSet getJarsetDependencies() 
	{
		return jarsetDependencies;
	}

	public Class<?> getCls()
	{
		return this.cls;
	}
	
	public double getResult() 
	{
		return (double)this.jarsetDependencies.size();
	}

}// End class SAfferent
