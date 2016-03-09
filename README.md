# Assignment

This project contains two module

- web-service
  - Spring Boot application

- client
  - AngularJS client
  

# Live demo
  
  [https://shrouded-tundra-33950.herokuapp.com/#/](https://shrouded-tundra-33950.herokuapp.com/#/)

### Create Company
![screenshot](https://cloud.githubusercontent.com/assets/17736798/13641998/bdb6f802-e640-11e5-8b38-578e9ee7b54f.png)


### Update Company
![screenshot](https://cloud.githubusercontent.com/assets/17736798/13641975/a4d4e79a-e640-11e5-9ce6-c4b1ac1f90aa.png)

 
# Overview

  Create a tiny REST / JSON web service in Java using a framework (e.g. Spark, Spring MVC
  (RestController) or similar) with an API that supports the following:

  - Create new company
  - Get a list of all companies
  - Get details about a company
  - Able to update a company
  - Able to add beneficial owner(s) of the company

  A company has the following attributes:

  - Company ID
  - Name
  - Address
  - City
  - Country
  - E­mail (not required)
  - Phone Number (not required)
  - One or more beneficial owner(s)


# Features

  - Integration with database migration tool- [FlyWay](http://flywaydb.org/)
  - RESTful APIs using Spring Boot
  - Database- In memory H2 database.
  - Unit Testing- Junit, EasyMock, H2 database and Spring Test Suite

# Technologies used
   - Java
   - Spring Boot
   - Hibernate
   - H2 Database
   - Gradle
   - Flyway

# Setting up development environment (On Ubuntu)
  Instructions to install and configure any prerequisites for the development environment

## Installing Java

   - sudo apt-get install python-software-properties

   - sudo apt-get install software-properties-common

   - sudo add-apt-repository ppa:webupd8team/java

   - sudo apt-get update

   - sudo apt-get install oracle-java8-installer

   - set JAVA_HOME in /etc/profile (if not set automatically by above commands)

   - Open /etc/profile and edit with following content

    ```

      JAVA_HOME=/usr/lib/jvm/java-8-oracle
      PATH=$PATH:$HOME/bin:$JAVA_HOME/bin
      JRE_HOME=/usr/lib/jvm/java-8-oracle
      PATH=$PATH:$HOME/bin:$JRE_HOME/bin
      export JAVA_HOME
      export JRE_HOME
      export PATH
  ```
## Installing Gradle

   - download gradle binary distribution from http://gradle.org/gradle-download/

    - unzip gradle binary and move it to /opt directory

    - open /etc/profile and set GRADLE_HOME, example given below

      - export GRADLE_HOME=/opt/gradle

## Database Setup

  This project uses in memory H2 database for local setup.


## Database migration

   - All database migration scripts are available in db/migration directory within project.

   - This project has FlyWay integrated, so you don't need to run db migration scripts manually. Flyway will automatically
   run those scripts at the time of server startup.

# Running

    - You can run this project from the command-line from the root directory(web-service) of project:

   ```
   $ gradle build
   $ java -jar build/libs/webservice-1.0-SNAPSHOT.jar
   ```

   - This command creates in memory H2 database and tables with seed data and run application on embedded Jetty server.


   - After startup, your instance will be available on localhost, port 8080.

  ```
  http://localhost:8080/
  ```

   - To open H2 console here

   ```
   http://localhost:8080/h2-console
   ```



# Curl Commands

   - Create company

   ```
   curl -i -X POST -H "Content-Type:application/json" -d '{  "name" : "Healthkart", "address": "HN-200" , "city" : "Gurgaon" , "country" : "India" , "email" : "info@hc.com",  "phoneNumber" : "9098767654" }' http://localhost:8080/api/company/
   ```

   - Get all company

   ```
   curl -i -X GET -H "Content-Type:application/json"  http://localhost:8080/api/companies
   ```

   - Get company details

   ```
    curl -i -X GET -H "Content-Type:application/json"  http://localhost:8080/api/company/<company-name>
    ```

   - Update company

   ```
  curl -i -X PUT -H "Content-Type:application/json" -d '{  "name" : "Healthkart", "address": "HN-200. Bata Chowk" , "city" : "Gurgaon" , "country" : "India" , "email" : "info@hc.com",  "phoneNumber" : "9098767654" }' http://localhost:8080/api/company/
    ```

   - Add beneficial user

    ```
    curl -i -X POST -H "Content-Type:application/json" -d '{  "name" : "Frodo" }' http://localhost:8080/api/company/<company-id>
    ```
