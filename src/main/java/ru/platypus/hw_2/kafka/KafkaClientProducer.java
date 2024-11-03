package ru.platypus.hw_2.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
//@Slf4j
public class KafkaClientProducer {

    private final KafkaTemplate template;

    public void send(Long id) {
        try {
            template.send("clientsRegistered",UUID.randomUUID().toString(), id).get();
            template.flush();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void sendTo(String topic, Object o) {
        try {
            template.send(topic, o).get();
            template.flush();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
