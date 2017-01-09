package ie.gmit.sw.controller;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import ie.gmit.sw.model.DatabaseRecord;

public class DatabaseOperations 
{
	private ObjectContainer db;
	
	public void openDB()
	{
		
		db = Db4oEmbedded.openFile("db");
		
	}

	
	public ObjectSet<DatabaseRecord> retrieveAll()
	{
		
		// Get data from database and store in record
		ObjectSet<DatabaseRecord> record = db.query(DatabaseRecord.class);
		
		return record;
		
	}// End method retrieveAll

	// ======
	// Getter
	// ======
	public ObjectContainer getDb() 
	{
		return db;
	}

}// End class DatabaseOperations
