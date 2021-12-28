## Technologies used in the project:

- Java 11
- Spring Boot
- ThymeLeaf
- H2 in-memory database
- Maven

### The project is deployed on AWS EC2 instance and is available on [http://lerkasan.de](http://lerkasan.de)

## How to start this project application locally

System Prerequisites - Required software to be installed:
- [Java SE 11 JDK](https://www.oracle.com/java/technologies/javase-downloads.html) (_**Note:**_ Please make sure to set the **JAVA_HOME** and the **PATH** environment variables)
- [Git](https://git-scm.com/downloads)

_**Useful links:**_
- [Setting the **PATH** Environment Variable in Windows](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html#GUID-96EB3876-8C7A-4A25-9F3A-A2983FEC016A)
- [Setting the **JAVA_HOME** Variable in Windows](https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html)
  (_**Note:**_ Also in Windows environment variables may be set as follows:
  Right click on Computer -> Advanced system settings -> Select Advanced tab -> Click on Environment variables)

### Steps to run the project application using command prompt
1. Download the project source code from GitHub repository:
   `git clone https://github.com/lerkasan/members.git`


2. Go to the folder with downloaded source code:
   
   `cd members`


3. Compile the source code and run tests using Maven Wrapper by typing a following command into command prompt (cmd for Windows or sh/bash for Linux):
   
   `mvnw.cmd clean install` (for Windows) or
   
   `./mvnw clean install` (for Linux)
   

4. Run the application using Maven Wrapper by typing a following command into command prompt (cmd for Windows or sh/bash for Linux):
   
   `mvnw.cmd spring-boot:run` (for Windows) or
   
   `./mvnw spring-boot:run` (for Linux)


5. Open [http://localhost:8080](http://localhost:8080) in a web browser. If needed, you can connect to an in-memory H2 database by opening [http://localhost:8080/h2-console](http://localhost:8080/h2-console) 

   _**In-memory database credentials:**_
   
   Username: sa
   
   Password: password
   
   Database name: db
   
   JDBC URL: jdbc:h2:mem:db
   
   Driver Class: org.h2.Driver


6. To stop the application press **Ctrl+C** in a command prompt.
