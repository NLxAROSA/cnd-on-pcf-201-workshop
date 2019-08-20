package io.pivotal.workshops.cnd.cachingworkshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
 * DemoController
 */
@RestController
public class DemoController {

    @GetMapping
    public Mono<String> getMessage()    {
        String message = "This is a sample test message";
        return Mono.just(message);
    }
    
}