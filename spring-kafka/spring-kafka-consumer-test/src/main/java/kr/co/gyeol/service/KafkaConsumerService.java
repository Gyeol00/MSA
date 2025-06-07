package kr.co.gyeol.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class KafkaConsumerService {

    // 이벤트. 토픽에 데이터가 들어가는 것은 이벤트라서 리스너
    // 리스닝 하려는 topic
    @KafkaListener(topics = "my-topic-01", groupId = "group-my-topic-01")

    // 이 메서드가 토픽에서 데이터를 가져옴
    public void topic01Consume(ConsumerRecord<String, String> record) { // <String, String> key, value라고 생각
        log.info("topic01Consume...");
        log.info(record.key() + "-" + record.value());

    }

    @KafkaListener(topics = "my-topic-02", groupId = "group-my-topic-02")

    // 이 메서드가 토픽에서 데이터를 가져옴
    public void topic02Consume(ConsumerRecord<String, String> record) { // <String, String> key, value라고 생각
        log.info("topic02Consume...");
        log.info(record.key() + "-" + record.value());

    }

    @KafkaListener(topics = "my-topic-03", groupId = "group-my-topic-03")

    // 이 메서드가 토픽에서 데이터를 가져옴
    public void topic03Consume(ConsumerRecord<String, String> record) { // <String, String> key, value라고 생각
        log.info("topic03Consume...");
        log.info(record.key() + "-" + record.value());

    }

}
