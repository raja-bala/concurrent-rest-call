package com.demo.concurrentrestcall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class RestCall {

    @GetMapping("/restcallsingleinstance")
    public String jobLauncherMaster() {

        final String uri = "http://localhost:8083";

        WebClient webClient = WebClient.create(uri);
        var test =   webClient.get()
                        .uri("/launch/master/job")
                        .retrieve()
                        .bodyToMono(String.class);
                test.subscribe(tweet -> System.out.println(tweet));

        var test1 =   webClient.get()
                .uri("/launch/master/job")
                .retrieve()
                .bodyToMono(String.class);
        test1.subscribe(tweet -> System.out.println(tweet));

        return "success";
    }

    @GetMapping("/restcalltwoinstances")
    public String jobLauncherMaster1() {



        WebClient webClient1 = WebClient.create("http://localhost:8083");
        var test =   webClient1.get()
                .uri("/launch/master/job")
                .retrieve()
                .bodyToMono(String.class);

        test.subscribe(tweet -> System.out.println(tweet));

        WebClient webClient2 = WebClient.create("http://localhost:8084");
        var test1 =   webClient2.get()
                .uri("/launch/master/job")
                .retrieve()
                .bodyToMono(String.class);

        test1.subscribe(tweet -> System.out.println(tweet));

        return "success";
    }
}
