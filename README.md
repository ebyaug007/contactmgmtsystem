 Contact-Management-System

## Overview
Below are the features
1. Able to add a contact with following data
      - Id (unique integer)
      - Name
      - Address
      - Phone (unique 10 digit)
      - Email
2. Delete a specific Contact.
3. Able to fetch details of all the Users.   

## Runtime variables

server.port=8082

## Technology Stack
- H2
- Spring Boot 2.7
- Java 11

## Running the application
1. Run the following command to create an executable jar file.
```
mvn clean package
```
2. Run the below command to start up the Spring Boot application.
```
java -jar target/ContentManagementSystem-0.0.1-SNAPSHOT.jar
```
## Swagger UI
http://localhost:8082/swagger-ui.html

## Endpoints
| URL                                       | HTTP Request Metods | Description                                 |
| ------------------------------------------|:-------------:      | :-------------------------------------------|
| http://localhost:8082/cms/addContact        |       post          | To add single Contact details.                 |
| http://localhost:8082/cms/getAllContacts    |       get          | To get the details of all Contacts.                |
| http://localhost:8082/cms//cms/deleteContact/{id} |       delete           | To delete the details of a contact.               |

