# Project Title

CO2 emission calculator

## Project Description Started

To create a program that returns the amount of CO2-equivalent that will be caused when traveling between two cities using
a given transportation method.

### Prerequisites (Development Environment)

In System 

Install JDK8
Install Maven 
Install Eclipse for Java 


### Compiling Code as done in Windows10

1. Save the Project "Co2Emissioner.zip" in Local system
2. Unzip Co2Emissioner.zip
3. Create an API token in https://openrouteservice.org/ 
   The generate API token is stored is system using environment variable called "ORS_TOKEN"
4. Copy the file "co2emissionerdata.csv" from the unzipped folder and save it in local system at some location, say "D:\".
5. Set environment variable "CO2_EMISSION_CSV_FILE_PATH" with the value of "D:\co2emissionerdata.csv"

6. Start Eclipse
7. Import this project "Co2Emissioner" [ Example:  File->Open Projects from File System->Brows the copied project path]
8. Right Click On Project in Eclipse Package Explorer -> Maven clean
9. Right Click On Project in Eclipse Package Explorer -> Maven install
   -> It will create a jar under "target" folder by name "co2emissioner-0.0.1-SNAPSHOT-jar-with-dependencies.jar"

### Running executable jar
   
10. Open command prompt -> Change directory to "target" folder

11. Run the below command -

"java -jar co2emissioner-0.0.1-SNAPSHOT-jar-with-dependencies.jar --start Hamburg --end Berlin --transportation-method medium-diesel-car"

It will run the jar and print the output in Console as below 

Console Output:  Your trip caused 49.248kg of CO2-equivalent.

12. Test Run : 

java -jar co2emissioner-0.0.1-SNAPSHOT-jar-with-dependencies.jar --start Hamburg --end Berlin --transportation-method medium-diesel-car

 Your trip caused 49.248 kg of CO2-equivalent.


java -jar co2emissioner-0.0.1-SNAPSHOT-jar-with-dependencies.jar --start "Los Angeles" --end "New York" --transportation-method=medium-diesel-car

 Your trip caused 770.3857800000001 kg of CO2-equivalent.


java -jar co2emissioner-0.0.1-SNAPSHOT-jar-with-dependencies.jar --end="New York" --start "Los Angeles" --transportation-method=large-electric-car

 Your trip caused 328.87814000000003 kg of CO2-equivalent.


D:\Ananta\Study\gitHubProjects\Co2Emissioner\ananta.co2emissioner\target>

13.  To Print Logs - run with extra parameter at end of the command "--debug true"  

"java -jar co2emissioner-0.0.1-SNAPSHOT-jar-with-dependencies.jar --start Hamburg --end Berlin --transportation-method medium-diesel-car --debug true"
   
  
## Note: Basic error handling are added. More error handling can be added similar way if more time spent !

## Thanks You . 