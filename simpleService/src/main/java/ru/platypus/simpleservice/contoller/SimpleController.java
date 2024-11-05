package ru.platypus.simpleservice.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("echo/{message}")
    public String echo(@PathVariable String message) {
        return message;
    }
}
