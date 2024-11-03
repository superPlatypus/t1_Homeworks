package ru.platypus.hw_2.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.platypus.hw_2.dto.ClientDto;
import ru.platypus.hw_2.entity.Client;
import ru.platypus.hw_2.service.ClientService;
import ru.platypus.hw_2.util.ClientMapper;

import java.util.List;
@Component
@RequiredArgsConstructor
public class KafkaClientConsumer {


    private final ClientService clientService;

    @KafkaListener(topics = "clientsToRegistration", groupId = "client_group")
    public void listenGroupFoo(List<ClientDto> messageList,
                               Acknowledgment ack) {
        System.out.println(messageList);
        try {
            List<Client> clients = messageList.stream()
                    .map(dto -> {
                        dto.setFirstName(dto.getFirstName());
                        return ClientMapper.toEntity(dto);
                    })
                    .toList();
            clientService.registerClients(clients);
        } finally {
            ack.acknowledge();
        }
    }
}
