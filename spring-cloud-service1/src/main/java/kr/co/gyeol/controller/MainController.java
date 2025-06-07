package kr.co.gyeol.controller;

import brave.Span;
import brave.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MainController {

    private final Tracer tracer;
    private final WebClient webClient; // config에 bean 등록한 메서드. 다른 서비스에 요청을 해주는 객체

    @GetMapping("/")
    public String index() {
        return "Service1 index!!!";
    }

    @GetMapping("/traceId")
    public String traceId() {

        log.info("traceId...");

        Span currentSpan = tracer.currentSpan();

        String traceId = currentSpan.context().traceIdString();
        String spanId = currentSpan.context().spanIdString();

        return "[traceId : ]" + traceId + " [spanId : ]" + spanId;
    }

    @GetMapping("/user")
    public Mono<Map<String, String>> user() {

        log.info("service1 user...");
        
        webClient.get().uri("/user").retrieve().bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {}); // get().uri("/user")의 주소 http://localhost:8082/service1/user

        // mono지만 map이다
        Mono<Map<String, String>> monoUser = webClient.get()
                .uri("/user")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {});

        return monoUser;
        
    }

}
