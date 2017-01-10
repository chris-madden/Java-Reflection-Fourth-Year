package ie.gmit.sw.controller;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * This class is used to calculate the couplings of the classes in the jar file.
 * It calculates the number of efferent and afferent couplings and the instability result of each class
 *
 */
public class Measurement 
{

	private List<Efferent> listCE = new LinkedList<Efferent>();
	private List<Afferent> listCA = new LinkedList<Afferent>();
	private List<Result> result = new LinkedList<Result>();
	private ClassSet classSet;
	
	/**
	 * Constructor is used for initializing a CLassSet list
	 * 
	 * @param classSet A ClassSet type is passed into the constructor
	 */
	// Receives a set of the classes in the jar file
	public Measurement(ClassSet classSet) 
	{
		this.classSet = classSet;
	}

	/**
	 * This method fills an afferent list and efferent list with their couplings
	 * 
	 */
	public void getCouplings()
	{
		
		// Populate efferent list with efferent couplings 
		for (int i = 0; i < this.classSet.size(); i++)
		{
			
			Efferent ef = new Efferent(this.classSet.get(i), this.classSet);
			
			this.listCE.add(ef);
			
		}
		
		// Populate afferent list with afferent couplings
		for (int i = 0; i < this.classSet.size(); i++)
		{
			
			Afferent af = new Afferent(this.classSet.get(i), this.listCE);
			
			this.listCA.add(af);
			
		}
		
	}
	
	/**
	 * 
	 *  This method calculates the number of afferent and efferent couplings as well as 
	 *  getting the instability score. These are then passed to Result.
	 * 
	 */
	public void calculateInstabilities()
	{
	
		double ceScore, caScore, instabiltiyScore;
		
		Result instabilityResult;
		
		for(int i = 0; i < classSet.size(); i++){
			
			Class<?> cls = classSet.get(i);
			
			ceScore = 0;	
			caScore = 0;
			instabiltiyScore = 0; 
			
			for(int j = 0; j < this.listCE.size(); j++)
			{
				
				Efferent efferent = this.listCE.get(j);
				
				if(cls.equals(efferent.getCls()))
				{
					ceScore = efferent.getResult();
					
					break;
				}
				
			}
			
			for(int j = 0; j < this.listCA.size(); j++)
			{
				
				Afferent afferent = this.listCA.get(j);
				
				if(cls.equals(afferent.getCls()))
				{
					caScore = afferent.getResult();
					
					break;
				}
				
			}
			
			if(ceScore == 0 && caScore == 0)
			{
				instabiltiyScore = 0;
			}
			else
			{
				instabiltiyScore = ceScore / (ceScore + caScore);
			}
				
			instabilityResult = new Result(cls, ceScore, caScore, instabiltiyScore);
			
			result.add(instabilityResult);
			
		}
	}

	/**
	 * Method allows retrieval of an list of type Efferent
	 * 
	 * @return Returns a list of Efferent types
	 */
	public List<Efferent> getListCE()
	{
		return listCE;
	}

	/**
	 * Method allows retrieval of an list of type Afferent
	 * 
	 * @return Returns a list of Afferent types
	 */
	public List<Afferent> getListCA()
	{
		return listCA;
	}

	/**
	 * Method allows retrieval of an list of type Result
	 * 
	 * @return Returns a list of Result types
	 */
	// Result contains list with instability scores
	public List<Result> getResult()
	{
		return result;
	}

}// End class Measurement
