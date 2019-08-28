# Exercise 1 - Scaling
This part of the PCF 201 Workshop focuses on the topic of scaling. In this part of the workshop you will learn to:

- Deploy an application
- Enable the autoscaler
- Set minimum and maximum instances for your application
- Add a scaling rule based on HTTP throughput
- Add a scaling rule based on a custom metric
- Add a scaling rule based on the comparison of two custom metrics
- Configure scheduled limits


## Build and deploy an application

Create a new Spring Boot application and add the `Web` or `Webflux` starters. Add a simple RestController that returns a String. After creating this app build it and push it to PCF.


```bash
./mvnw package
cf push
```

## Enable the autoscaler

Once the application has been deployed, open up the apps manager by pointing your browser to the following url: [https://apps.sys.lropcf.pushto.cf](https://apps.sys.lropcf.pushto.cf)

Locate your space and applications and click on the name of your application.

In the following screen, click 'Enable Autoscaler'

After a few seconds, the autoscaler should be up and running and you should see a second instance being added (the default minimum number of instances for the autoscaler is two). 

## Set minimum and maximum instances for your application

Change the minimum number to 3 and the maximum number to 4 and see what happens. Now change it to a minimum of 1 and a maximum of 3 and again see what happens.

## Add a scaling rule based on HTTP throughput

## Add a scaling rule based on a custom metric

In this part of the exercise, we'll add a custom metric in our application using Micrometer, store it in Redis (since we want to share it across all our application instances) and then expose it via a Prometheus endpoint so it can be consumed by PCF Metrics and the Autoscaler.

In the pom.xml file of your application, add the following two dependencies:

```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

In your application, add a `RedisConfig` class according to the example below:

```java
@Configuration
class RedisConfig {
    @Bean
    public RedisAtomicLong redisAccessLogCounter(RedisConnectionFactory connFactory) {
        return new RedisAtomicLong("workshop.request.counter", connFactory);
    }
}
```

Next, add a `RequestCounter` class according to the example below:

```java
@Component
public class RequestCounter {

    public RequestCounter(RedisAtomicLong redisRequestCounter)  {
        this.redisRequestCounter = redisRequestCounter;
    }

    private final RedisAtomicLong redisRequestCounter;

    public void incrementCounter() {
        redisRequestCounter.incrementAndGet();
    }

    public long getCounter() {
        return redisRequestCounter.get();
    }

    public void resetCounter() {
        redisRequestCounter.set(0L);
    }
}
```

And last, but certainly not least: add a `RequestCounterGaugeMicrometer` class:

```java
@Configuration
public class RequestCounterGaugeMicrometer {

    public RequestCounterGaugeMicrometer(RequestCounter requestCounter) {
        this.requestCounter = requestCounter;
    }

    private final RequestCounter requestCounter;

    @Bean
    public Gauge accessLogCounter(MeterRegistry registry) {
        return Gauge.builder("workshop.request.counter", () -> requestCounter.getCounter())
                .tag("kind", "performance")
                .description("Request count")
                .register(registry);
    }
}
```

To summarize: we added Redis connectivity in the application, added a metric in Redis and exposed this in a MeterRegistry. Under the hood, Spring Boot and Micrometer expose this metric into a Prometheus endpoint.

Of course we also want to be able to test this locally, so let's add an embedded Redis just for testing purposes. In your pom.xml file, add the following dependency:

```xml
<dependency>
    <groupId>it.ozimov</groupId>
    <artifactId>embedded-redis</artifactId>
    <version>0.7.2</version>
    <scope>test</scope>
</dependency>
```


workshop_request_counter metric