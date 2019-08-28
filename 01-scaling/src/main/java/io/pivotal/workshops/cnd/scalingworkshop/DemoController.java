package io.pivotal.workshops.cnd.scalingworkshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class DemoController {

    private final RequestCounter counter;

    public DemoController(RequestCounter requestCounter)    {
        this.counter = requestCounter;
    }

    @GetMapping
    public Mono<String> getMessage()    {
        counter.incrementCounter();
        String message = "Number of received requests: " + counter.getCounter();
        return Mono.just(message);
    }
    
}