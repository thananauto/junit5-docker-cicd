# junit5-docker-cicd

This Dockerfile is designed to set up an Alpine Linux-based container for running Maven projects with OpenJDK 11. It assumes your project structure includes source code in the src directory and a pom.xml file at the root..

## Sample Docker file
```Docker
# Use Alpine Linux 3.14 as the base image
FROM alpine:3.14

# Update package index and install OpenJDK 11 and Maven
RUN apk update && apk add openjdk11 && apk add maven

# Set the working directory to /app
WORKDIR /app

# Copy the source code from the local machine to the container's /app directory
COPY src src

# Copy the Project Object Model (POM) file to the container's /app directory
COPY pom.xml .

# Define the entry point for the container, specifying Maven as the command to run
ENTRYPOINT ["mvn"]

# Set the default command for the container to run the "test" goal when started
CMD ["test"]


```

## Dockerfile Instructions
 * `FROM:`  Specifies the base image (Alpine Linux 3.14).
* `RUN:` Updates the package index and installs OpenJDK 11 and Maven.
* `WORKDIR:` Sets the working directory to /app.
* `COPY:` Copies the source code and POM file from the local machine to the container.
* `ENTRYPOINT:` Defines the default executable to be run when the container starts.
*`CMD:` Specifies default arguments to the *`ENTRYPOINT`*, meaning when the container starts, it will run mvn test.

## Usage
 Build the docker image
```bash
docker build -t my-maven-app .
```
 Run maven tests with default `test` command
```bash  
docker run -it -v `pwd`/output:/app/target my-maven-app

```
Run maven tests with `verify` command
```bash  
docker run -it -v `pwd`/output:/app/target my-maven-app verify

```

Run maven tests with profile
```bash  
docker run -it -v `pwd`/output:/app/target my-maven-app verify -P profile

```

After successfull exit(0) of container, the execution result can be found in `output` directory