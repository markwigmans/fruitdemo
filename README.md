# Fruit Demo
This is a demo application, using [Spring Boot](https://spring.io/projects/spring-boot), [Angular](https://angular.io/) 
and [Spring Config Server](https://cloud.spring.io/spring-cloud-config/reference/html/) 
to run in a [Docker](https://www.docker.com/) context.

# Getting Started

## Requirements
The following tools are needed:

- maven 3.5+
- Java 11
- Docker 19+

## Maven Target

| target | meaning |
| ------- | ------ |
mvn clean | clean environment
mvn install | build self executable JAR files
mvn install -P docker | build docker images

## Configuration
Configuration is done via Spring Cloud Config

### Backend

| Property | Description | Default |
| -------- | ----------- | ------- |
| spring.datasource.url | Spring Boot database URL
| spring.datasource.username | Spring Boot database username
| spring.datasource.password | Spring Boot database password

### Frontend

| Property | Description | Default |
| -------- | ----------- | ------- |
| backend.url | Url where to find the REST interface of the backend | http://localhost:8080 |

## Run Application
A docker compose script is provided. The directory *config-repo* contains the configuration used. Start the application with:

``
docker-compose up
``

To start the scaling version do:

``
docker-compose -f docker-compose-scale.yml up --scale frontend=2 --scale backend=2
``

The ports used, are on purpose different from the application defaults, 
to be sure you test against the *runable jar* vs docker version. 

| component | port (runable jar) | port (docker) |
| --------- |:--------:|:-----------:|
| frontend | 8081 | 9081
| backend  | 8080 | 9080

The application works with a MySQL database as well. To make this work, do the following:
- modify the backend configuration accordingly;
- if needed uncomment the MySQL service in the *docker-compose* file.

# Background Information
## Example Config Call
The following call requests the backend information from the configuration server 
[Backend Configuration](http://localhost:8888/backend/development/master).

## REST interface
The REST interface of the backend is documented with OpenAPI and can be found (docker version): 
[Backend REST Interface](http://localhost:9080/swagger-ui.html).

## Background Information
- [Building a Web Application with Spring Boot and Angular](https://www.baeldung.com/spring-boot-angular-web);
- [How to use environment variables to configure your Angular application without a rebuild](https://www.jvandemo.com/how-to-use-environment-variables-to-configure-your-angular-application-without-a-rebuild/);
- [How to Use Docker Compose to Run Multiple Instances of a Service in Development](https://pspdfkit.com/blog/2018/how-to-use-docker-compose-to-run-multiple-instances-of-a-service-in-development/).

