package ru.platypus.hw_2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.platypus.hw_2.dto.ClientDto;
import ru.platypus.hw_2.kafka.KafkaClientProducer;
import ru.platypus.hw_2.service.ClientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final KafkaClientProducer kafkaClientProducer;
    @Value(value = "${spring.kafka.topic.clientsToRegistration}")
    private String clientsToRegistration;


    @GetMapping(value = "/parse")
    public void parseSource() {
        List<ClientDto> clientDtos = clientService.parseJson();
        clientDtos.forEach(dto -> kafkaClientProducer.sendTo(clientsToRegistration, dto));
    }
}