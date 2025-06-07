package kr.co.gyeol.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
public class MainController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    // publich 데이터 생성해서 발행 (topic으로 넣는 것)
    @GetMapping("/publish/topic01")
    public String publich1() {

        // 가상 대량 데이터 생성
        // 데이터가 생성되면 kafka로 데이터 들어감
        for (int i = 0; i < 2500; i++) {
            // "message-" + i 가 데이터
            kafkaTemplate.send("my-topic-01", "message-" + i);
        }

        return "done";
    }

    @GetMapping("/publish/topic02")
    public String publich2() {

        // 가상 대량 데이터 생성
        for (int i = 0; i < 2500; i++) {
            // "message-" + i 가 데이터
            kafkaTemplate.send("my-topic-02", "message-" + i);
        }

        return "done";
    }

    @GetMapping("/publish/topic03")
    public String publich3() {

        // 가상 대량 데이터 생성
        for (int i = 0; i < 2500; i++) {
            // "message-" + i 가 데이터
            kafkaTemplate.send("my-topic-03", "message-" + i);
        }

        return "done";
    }

}
