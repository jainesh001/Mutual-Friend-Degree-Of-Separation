# Mutual friend distance App

App lets you find one's friend at given distance.

# Mutual friend distance App

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)

App lets you find one's friend at given distance.

## Requirements

For building and running the application you need:

- [JDK 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)

##Complete App source can be found at:
BitBucket URL: https://bitbucket.org/jainesh001/version1

## Running the application locally

To run a Spring Boot application on your local machine. Import the project from pom.xml and One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so: If you dont have maven use mvnw instead mvn

```shell
mvn package
```
*Locate jar from target and run jar 
```shell
[java -jar mutualFriendDistance-0.0.1-SNAPSHOT.jar]
```
or

```shell
mvn spring-boot:run
```
Local access URL: http://localhost:5000/mutualFriendDistance/api/  [for more api Please refer postman api collection for url]

## Using the AWS EC2 instance

AWS URL: ec2-15-207-123-29.ap-south-1.compute.amazonaws.com/mutualFriendDistance/api/[for more api Please refer postman api collection for url]


## Database connection step to verfiy the result

*Requirement- MySQL Workbench or Dbever [Any sql client connector]
Hostname: aa1fc24j8xq84z4.cocydytlfhue.ap-south-1.rds.amazonaws.com
port: 3306
password: ASTRO001
[Local and EC2 instance both pointing to AWS DB]

After successful login
Database name: ebdb
tables : users,friends
[please refer attach AWS setup quriers file for saved queries for reference]

