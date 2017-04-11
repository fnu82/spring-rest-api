# spring-rest-api
Spring Boot Rest API for file management
Technology used:
Spring boot, spring data JPA, In memory DB(H2)

Prerequisites:
Java 8
Maven 3

Build and run:
Go to the project's root folder, then type:

$ mvn spring-boot:run

From Eclipse (Spring Tool Suite)

Import as Existing Maven Project and run it as Spring Boot App.

Usage:

Run the application and go to http://localhost:8080/
Use the following urls to invoke controllers methods and see the interactions with the In memory database(H2) and local disk file:

http://localhost:8080/file/saveToDisk
Using latest POSTMAN, click on params tab. Then enter key="file" and value=select type as file and then attach a file which needs to be uploaded. Hit SEND button.

http://localhost:8080/file/saveToDB
Using latest POSTMAN, click on params tab. Then enter key="fileMetaData" and value={"author":"yourname","fileName":"test"}. Enter another key="fileContent" and value=select type as file and then attach a file which needs to be uploaded. Hit SEND button.

http://localhost:8080/file/search/2
Searches by file ID. Returns file meta data as well as content in binary format.

http://localhost:8080/file/searchByName/file_name
Searches by file name and downloads the file to disk.
Hit "Send and Download" button on POSTMAN.

