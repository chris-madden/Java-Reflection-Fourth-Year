# Measuring Stabilty with Reflection API

**Student Name:** Christy Madden <br />
**Student ID:** G00214065 <br />
**Module:** Advanced Object Oriented Software Development<br />
**Lecturer:** Dr John Healy <br />
**GitHub:** https://github.com/chris2020/Java-Reflection-Fourth-Year <br />

# Introduction

### What is the application?

This application tests the stabilty of classes by checking their dependencies and calculting their Positional Stability. This is done by finding the number of Afferent couplings (Ca) and Efferent couplings (Ce) in a class. Once these have been found, the class's Positional Stability (I) can be caluculated using the formula _I = Ce / Ca + Ce_. To find the dependencies in each class the Refection API is used. The application also shows the use different design patterns. Finally the application demonstates use of the Javax Swing API. When the application is run the user is shown a GUI in which they can interact with the project.

### What does the appication run on?

This application is built using Java and will run on any machine or OS once it has Java installed. It is recommended that your computer has Java 8 installed. 

### What Libraries does the application use?

* The Reflection API is for finding dependencies
* The Javax Swing API is used to build the GUI
* The Java awt API is used for some graphical manipulations
* The DB4O API is used for creating and storing information in a database

### How can the application be deployed?

The application can be downloaded from GitHub using the above link. You will be need to run it from the command line. It is recommended that you navigate to the root directory of the project. Here you can run the command that will start the application

 **Note** _you will need to have a jar file you want the application to run and you will need to be add it to the classpath_

 **Example command to run** 

 ```
java -cp ./bin:./lib/people.jar:./lib/db4o.jar ie.gmit.sw.launch.Runner ./lib/people.jar

 ```
 * __java__ is the command needed to run a java application
 * __-cp__ is an argument that will add the required jar files to the classpath. Add the bin folder and the db4o jar to the classpath as well as the jar file you want to test
 * The db4o jar is supplied to you in the lib folder as well as a test jar called __people.jar__ to get you started
 * The class to run is Runner.java and is found in the __ie.gmit.sw.launch__ package
 * The last argument passes the jar file into the application so __be sure to provide the path to the jar file you want to test__

 # Technologies used

 **Operating System:** Linux Mint 17.3 “Rosa” <br />
 **IDE:** Eclipse Neon 4.6.2 <br />
 **Software Version Control:** Git 1.9.1 <br />
 **Hosting Site:** GitHub <br />
 **Programming Language:** Java 8 <br />
 **UML Generator:** ObjectAid
 **Documentation:** JavaDocs

 # Features

 ### Design Patterns

 The project maked use of multiple design patterns. Below, Each one used will be described and explained.

 ##### MVC Pattern #####
 This pattern was used as the application had a GUI and a database. It made sense to divide up the code into this pattern as it's a very popular pattern for this type of application. The __view__ package contains the code for the GUI, the __model__ package contains the code for the datbase records and the __controller__  package contains the code that procesees data and passes it from the view to the controller and vice versa.

 **Package Structure**

 ```
Project
|---------------------------------|
|----controller                   |
|    |-- Afferent.java            |
|    |-- Calculator.java          |
|    |-- ClassHandler.java        |
|    |-- ClassSet.java            |
|    |-- DatabaseOperations.java  |
|    |-- Efferent.java            |
|    |-- Loadable.java            |
|    |-- LoadJarClasses.java      |
|    |-- Measurement.java         |
|    |-- Result.java              |
|    |-- SingletonRecord.java     |
|                                 |
|----model                        |
|    |-- DatabaseRecord.java      |
|                                 |
|----view                         |
|    |-- UserView.java            |
|---------------------------------|

```

 ##### Strategy Pattern #####
 This pattern is used to make it easy to extended the program. In this project it allows for reading jar files but it would be very easy to add the functionality to read files from other sources E.G From other zip archives. This is down to the fact that you are programming to an abstraction with this pattern and creating a new concrete class that extends the interface Loadable will enable you to easily create other methods of reading in classes.

 **Example Code**
 
 ```Java

    public interface Loadable 
    {
	
        // Load classes from some source 
        public ClassSet load(String nameOfSource) throws FileNotFoundException, IOException;

    }

 ```

 ##### Singleton Pattern #####
 This pattern is used in the program as only one instance of a database record is needed per session. It's flexible enough to intialise fields of the database record in different parts of the project and when all data for the database record has been initialize it returns the record ready to be saved into the database. This pattern was able to solve a problem where data needed was found in two completely different parts of the code, it made the code to save a database record cleaner and easier to program.

**Example Code**

```Java

    // Create static instance of SingletonRecord
    private static SingletonRecord uniqueRecord = new SingletonRecord();

    private SingletonRecord(){};

    // Return single instance of SingletonRecord
    public static SingletonRecord getInstance()
    {
        return uniqueRecord;
    }

```

 **Note** _The database connection should have been handled with the Singleton pattern but it had been too deeply encoded by the time this was realised_

  ##### Observer Pattern #####
  This pattern is used by the GUI and ita use can be seen when clicking buttons. The buttons ActionListener is activated when the button is clicked and the code connected to the button is run.

  **Example Code**

  ```Java

    // When run button is pressed
    runButton.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e)
        {
            
            if(runButtonClick == 0)
            {
                
                // Create object array with the length of 4
                Object[] row = new Object[columnNames.length];
                
                // Loop through table data array
                for(int i = 0; i < tableData.length; i++)
                {
                    
                    for(int j = 0; j < columnNames.length; j++)
                    {
                        
                        // Store a row from table data into a single dimension array
                        row[j] = tableData[i][j];
                        
                    }// End inner for
                    
                    // Add row to table
                    model.addRow(row);
                    
                }// End outer array
                
            }// End if
            
            runButtonClick++;
            
            // Feedback for user
            infoLabel.setText("Jar Results");
            
        }// End method actionPerformed
        
    });// End addActionListener

  ```

  ### Database

 ##### DB4O #####

 This project stores metadata about the jar and its files. The databsae used is DB4O and the jar file containing the API is included with the project in the lib folder. Information stored in single record includes:
 
 * Jar file name
 * Number of classes in the jar
 * Number of fully stable classes
 * Number of fully unstable classes 
 * Number of classes that are neither fully unstable or fully unstable

 **Example Code**

 ```Java

    // Method retrieves records from the database
    public ObjectSet<DatabaseRecord> retrieveAll()
	{
		
		// Get data from database and store in record
		ObjectSet<DatabaseRecord> record = db.query(DatabaseRecord.class);
		
		return record;
		
    }

 ```

 ### Reflection API

 This API was used to retrieve information about the classes in the jar file. It was used to find the following information:
 
 * The fields in the class
 * The parameters of any constructors 
 * Whether the class has a superclass other than the Object class
 * Whether the class has oe implements any interfaces
 * The parameters of any methods the class has
 * The method return types
 
 Using this API meant that a classes afferent and efferent couplings could be determined and this enabled the program to find the classes Positional Instablilty score.

 **Example Code**

```Java

    // Method checks for interfaces and stores them in a list
    public ClassSet getInterface(Class<?> cls) 
	{
		
        // A new list
		ClassSet inFace = new ClassSet();
		
        // Get interfaces
		Class<?> inFaceArray[] = cls.getInterfaces();
		
        // Add interfaces to list
		for(Class<?> c : inFaceArray)
		{
			inFace.add(c);
		}
		
		return inFace;
		
	}// End getInterface

```

### GUI

The application has a Graphical User Interface (GUI) which displays intformation about the jar file to the user and allows the user to interact with the program. It constists of:

* __Two tables__
    * One for jar information which includes: 
        * Class names
        * Afferent couplings
        * Efferent couplings
        * Positional instablilty score
    * One for displaying the information stored in the database, which includes:
        * Jar name
        * Number of classes in jar
        * Number of fully stable classes
        * Number of fully unstable classes
        * Number of class that are between fully stable and fully unstable 

* __Five buttons__
    * Run the program
    * Load data from database
    * Save to database
    * Delete all records in database
    * Clear rows from database display table 

* __Three labels__
    * Label to provide feedback to user when interacting with buttons
    * Labels to tell user which table is the jar table and which is the database table

* __Colour coding table cells__
    * The column for the positional stabilty whill highlight different colours depending on the score 
        * Column turns __green__ if class is fully stable
        * Column turns __red__ if class is fully unstable
        * Column remains __white__ if class is in between these vaules


**Example Code**

```Java

    // Create new JTable
    jtable = new JTable(model){
			   
        private static final long serialVersionUID = 3344805599300020343L;

        // Have to override the method prepareRenderer
        public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
            
            Component comp = super.prepareRenderer(renderer, row, col);
            
            // Store value in cell
            Object value = getModel().getValueAt(row, col);
        
            // Add color to cells depending on their stability
            if (value.equals(Double.valueOf(1)) && col == 3)
            {
                comp.setBackground(Color.red);
                
            }
            else if(value.equals(Double.valueOf(0)) && col == 3)
            {
                comp.setBackground(Color.green);
                
            } else 
            {
                comp.setBackground(Color.white);
            }
            
            return comp;
            
        }// End prepareRenderer
        
    };

```

# References 

##### Jar file #####
* The jar file _people.jar_ used for testing was created by Andrej Lavrinovic

##### Reflection API #####
* E-book: Java Reflection Tutorial The Ultimate Guide, Daniel Gutierrez Diez, 2014
* Website: https://www.javacodegeeks.com/

##### Java Swing API #####
* Book: Head First Java, Kathy Sierra and Bert Bates, 2005
* Website: https://docs.oracle.com/javase/tutorial/uiswing/

##### Design Patterns #####
* Book: Head First Design Patterns, Eric Freeman and Elisabeth Freeman with Kathy Sierra and Bert Bates, 2004