package ie.gmit.sw.controller;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import ie.gmit.sw.model.DatabaseRecord;

/**
 * 
 * This class contains methods that provide database operations
 *
 */
public class DatabaseOperations 
{
	private ObjectContainer db;
	
	/**
	 * Method opens up the database connection
	 */
	public void openDB()
	{
		
		db = Db4oEmbedded.openFile("db");
		
	}// End openDB

	/**
	 * Method is used to get records stored in database
	 * 
	 * @return Returns an ObjectSet of type DatabaseRecord
	 */
	public ObjectSet<DatabaseRecord> retrieveAll()
	{
		
		// Get data from database and store in record
		ObjectSet<DatabaseRecord> record = db.query(DatabaseRecord.class);
		
		return record;
		
	}// End method retrieveAll

	// ======
	// Getter
	// ======
	
	/**
	 * Method gets the database connection object 
	 * 
	 * @return Returns ObjectContainer for opened database
	 */
	public ObjectContainer getDb() 
	{
		return db;
	}

}// End class DatabaseOperations
