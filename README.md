# This project is an implementation of Game of life

## Dependencies
   * Java 1.8 or higher
   * Maven 3.6.x or higher

## Project structure
The main class of this application is GameApplication, placed under src/main/java/org/elephant/main

## Compile
``
 bash$ mvn clean install
``

## Run the application
```
bash$ mvn compile exec:java
```
## Customization 
The default plane size is configured to 25x25. The game supports configurable plane size. The plane size can be configured with boundary in GameApplication.java

Note: You can also run the application directly from the editor
