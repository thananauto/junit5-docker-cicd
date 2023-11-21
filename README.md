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

After successfull exit(0) of container, the execution result can be found in `output` directory of host

--------------------------

For more customisation we can use the  `ARG` and `ENV` command of Docker

```Docker
FROM alpine:3.14
RUN apk update && apk add openjdk11 && apk add maven
# build argument that can be passed during the build process
ARG name
WORKDIR /app
COPY src src
COPY pom.xml .
ENTRYPOINT ["mvn"]
# pass the name as mvn command line arguments
CMD ["test", "-Denv.name=${name}]
```

 Build the docker image with mandatory arguments name (required): Pass a custom value during the build process.
```bash
docker build --build-arg name=<your_argument_value> -t my-maven-app .

```

Once container starts the test will execute 
```bash  
docker run -it -v `pwd`/output:/app/target my-maven-app
```
Note: If we pass any `CMD` as after argument on start the of container the default maven command line argument will overwrite

For example
```bash  
docker run -it -v `pwd`/output:/app/target my-maven-app verify

```
This `verify` command will overwrite the `["test", "-Denv.name=${name}]` 

--------------------------------------------

## Environment variable
Environment variable is really handy when we try to spinup the container without modifiy the original `image`

```Docker
FROM alpine:3.14
RUN apk update && apk add openjdk11 && apk add maven
# environment variable could be assigned from -e during container initialization
ENV env_name $env_name
WORKDIR /app
COPY src src
COPY pom.xml .
ENTRYPOINT ["mvn"]
# pass the name as mvn command line arguments
CMD ["test", "-Denv.name=${env_name}]
```

 Build the docker image
```bash
docker build -t my-maven-app .
```
On starting the container pass the `-e` flag as well
```bash
docker run -e env_name=<your_value> my-maven-app

```

## Contributing
If you have suggestions or improvements, feel free to open an issue or submit a pull request.