## Cloud Native Development on PCF 201 - Workshop
This is the followup of the [Cloud Native Development on PCF 101 workshop](https://github.com/NLxAROSA/cnd-on-pcf-101-workshop) and will focus more in-depth on several topics of cloud native development. In this workshop you will develop several Spring Boot 2 micro services, deploy them to Cloud Foundry and learn how to setup several of the more advanced scenarios.

[Getting started with Pivotal Cloud Foundry](https://pivotal.io/platform/pcf-tutorials/getting-started-with-pivotal-cloud-foundry)


## Prerequisites

We expect you to bring:

* A laptop with Windows, Linux or MacOS
* A working internet connection
* An IDE or code editor of your choice

We expect you have installed:

* [JDK8 or higher](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Cloud Foundry CLI](https://docs.run.pivotal.io/cf-cli/install-go-cli.html)

We expect you to have completed the [Cloud Native Development on PCF 101 workshop](https://github.com/NLxAROSA/cnd-on-pcf-101-workshop) and are familiar with the basics of PCF, Java and Spring Boot.

Login credentials and access to the environment will be provided by the instructor(s).


## Verify PCF CLI is working

```bash
cf -v
```

Login to Cloud Foundry:

```bash
cf login -a https://api.sys.lropcf.pushto.cf
Email> <provided user>
Password> <provided password>
```


## Build the project

For every exercise we need to build the project: 

```bash
mvn clean package
```

In case you don't have Maven installed run: 

```bash
./mvnw clean package
```


## Run the application locally

Before we push the application to Cloud Foundry run it locally first!

```bash
./mvnw spring-boot:run
```

Open in the browser: [http://localhost:8080/](http://localhost:8080/)


## Exercises

* Exercise 1: [start](01-scaling/exercise-1-start.md), [solution](01-scaling/exercise-1-solution.md)
* Exercise 2: [start](02-security/exercise-2-start.md), [solution](02-security/exercise-2-solution.md)
* Exercise 3: [start](03-robustness-resilience/exercise-3-start.md), [solution](03-robustness-resilience/exercise-3-solution.md)
* Exercise 4: [start](04-caching/exercise-4-start.md), [solution](04-caching/exercise-4-solution.md)
* Exercise 5: [start](05-metrics-logging/exercise-5-start.md), [solution](05-metrics-logging/exercise-5-solution.md)
* Exercise 6: [start](06-messaging/exercise-6-start.md), [solution](06-messaging/exercise-6-solution.md)


## Tips

* Stuck? The exercises have hints to help you!
* Stuck? Ask your neighbour to pair with you!
* Stuck? Every exercise has a working solution in its own branch!
