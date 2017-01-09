package ie.gmit.sw.controller;

import java.util.List;

/**
 * 
 * This class is used to find afferent couplings in each class in the jar file. The class implements the 
 * interface Calculator so it has to fulfill its contract of calculating
 *
 */

public class Afferent implements Calculator{
	
	private ClassSet classSetDependencies;
	private Class<?> cls;
	private List<Efferent> listCE; 

	/**
	 *  This constructor is used to pass over and initialize the list of classes from jar file,
	 *  the list of efferent couplings and to call the calculate method
	 * 
	 * @param class1 This is a class passed in
	 * @param listCE This is a list of efferernt couplings
	 */
	public Afferent(Class<?> class1, List<Efferent> listCE)
	{
		
		this.cls = class1;
		this.listCE = listCE;
		
		calculate();
	}
	
	/**
	 * 
	 * This method calculates the afferent couplings and adds them to the classSetDependencies
	 * list. 
	 * 
	 */
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

	/**
	 * This method is used to get a list of CLasses that have dependencies
	 * 
	 * @return A list of type CLassSet is returned
	 */
	public ClassSet getJarsetDependencies() 
	{
		return classSetDependencies;
	}

	/**
	 * This method is used to get a particular class
	 * 
	 * @return A type of Class is returned
	 */
	public Class<?> getCls()
	{
		return this.cls;
	}
	
	/**
	 * This method is used to get the number of dependencies
	 * 
	 * @return A double is returned representing the size of the list
	 */
	public double getResult() 
	{
		return (double)this.classSetDependencies.size();
	}

}// End class Afferent
