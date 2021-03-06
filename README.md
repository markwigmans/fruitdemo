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
mvn versions:display-property-updates | check if latest libraries needs to be updated.

## Configuration
Configuration is done via Spring Cloud Config.

| Component | Configuration file
| --------- | ------------------ |
| backend   | backend.yml
| frontend  | frontend.yml

### Backend

| Property | Description | Default |
| -------- | ----------- | ------- |
| spring.datasource.url | Spring Boot database URL
| spring.datasource.username | Spring Boot database username
| spring.datasource.password | Spring Boot database password

### Frontend

| Property | Description | Default |
| -------- | ----------- | ------- |
| api.url | URL the Angular App connects to for the backend data | http://localhost:8081/backend
| backend.url | URL where to find the REST interface of the backend | http://localhost:8080 |

## Run Application
A docker compose script is provided. The directory *config-repo* contains the configuration used. Start the application with:

``
docker-compose --env-file env.dev up -d
``

To start the scaling version do:

``
docker-compose -f docker-compose-scale.yml --env-file env.dev up --scale frontend=2 --scale backend=2 -d
``

The ports used are on purpose different from the application defaults, to be sure you test against the *runable jar* vs docker version. 

| component | port (runable jar) | port (docker) |
| --------- |:--------:|:-----------:|
| frontend | 8081 | 9081
| backend  | 8080 | 9080

The application works with a MySQL database as well. To make this work, do the following:
- modify the backend configuration accordingly;
- if needed uncomment the MySQL service in the *docker-compose* file.

### Encrypted Config Parameters
Spring Config Server supports [encryption](https://cloud.spring.io/spring-cloud-config/reference/html/#_encryption_and_decryption) 
of parameters. Perform the following steps:

We assume that we the value to be encrypted is *secret*:

1. start the config server used;
1. run ``curl <config-server>/encrypt -d secret``
1. replace as property value: ```'{cipher}<encrypt response>'``` instead of *secret* value in the property file.

Parameters are encrypted via symmetrical encryption. The environment variable **CONFIG_KEY** defines the key used.

# Background Information
## Example Config Call
The following call, requests the backend information from the configuration server:
``http://localhost:8888/backend/development/master``.

## REST interface
The REST interface of the backend is documented with OpenAPI and can be found: 
``http://localhost:9080/swagger-ui.html``.

## Useful docker-compose commands
| command | meaning |
| ------- | ------- |
| ``docker-compose up``   | *start* all the docker containers in the docker-compose file
| ``docker-compose down`` | *stop* all the docker containers in the docker-compose file
| ``docker-compose pull`` | *update* all the docker images in the docker-compose file

## Related
- [Sonar Cloud](https://sonarcloud.io/dashboard?id=markwigmans_fruitdemo)
- [Docker Hub](https://hub.docker.com/u/markwigmans)

## Background Information
- [Building a Web Application with Spring Boot and Angular](https://www.baeldung.com/spring-boot-angular-web);
- [How to use environment variables to configure your Angular application without a rebuild](https://www.jvandemo.com/how-to-use-environment-variables-to-configure-your-angular-application-without-a-rebuild/);
- [How to Use Docker Compose to Run Multiple Instances of a Service in Development](https://pspdfkit.com/blog/2018/how-to-use-docker-compose-to-run-multiple-instances-of-a-service-in-development/).
- [Continuous deployment using Docker, GitHub Actions, and Web-hooks](https://levelup.gitconnected.com/automated-deployment-using-docker-github-actions-and-webhooks-54018fc12e32)
