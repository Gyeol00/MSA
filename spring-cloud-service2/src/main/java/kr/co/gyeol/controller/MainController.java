package kr.co.gyeol.controller;

import brave.Span;
import brave.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MainController {

    private final Tracer tracer;

    @GetMapping("/")
    public String index() {
        return "Service2 index!!!";
    }

    @GetMapping("/traceId")
    public String traceId() {

        log.info("traceId...");

        Span currntSpan = tracer.currentSpan();

        String traceId = currntSpan.context().traceIdString();
        String spanId = currntSpan.context().spanIdString();

        return "[traceId : ]" + traceId + " [spanId : ]" + spanId;
    }

    @GetMapping("/user")
    public Map<String, String> user() {

        log.info("service2 user...");

        return Map.of(
                "uid", "a101",
                "name", "홍길동",
                "age", "23",
                "addr", "부산"
        );
    }

}
