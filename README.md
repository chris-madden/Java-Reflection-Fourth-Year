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

 # Features

 ### Design Patterns

 The project maked use of multiple design patterns. Below, Each one used will be described and explained.

 ##### MVC #####
 This pattern was used as the application had a GUI and a database. It made sense to divide up the code into this pattern as it's a very popular pattern for this type of application. The __view__ package contains the code for the GUI, the __model__ package contains the code for the datbase records and the __controller__  package contains the code that procesees data and passes it from the view to the controller and vice versa.

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

