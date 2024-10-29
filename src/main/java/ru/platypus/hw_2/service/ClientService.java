package ru.platypus.hw_2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.platypus.hw_2.dto.ClientDto;
import ru.platypus.hw_2.entity.Client;
import ru.platypus.hw_2.kafka.KafkaClientProducer;
import ru.platypus.hw_2.repository.ClientRepository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final KafkaClientProducer kafkaClientProducer;
    private final ClientRepository repository;

    public void registerClients(List<Client> clients) {
        repository.saveAll(clients)
                .stream()
                .map(Client::getId)
                .forEach(kafkaClientProducer::send);
    }


    public List<ClientDto> parseJson() {
        ObjectMapper mapper = new ObjectMapper();
        ClientDto[] clients;
        try {
            clients = mapper.readValue(new File("src/main/resources/MOCK_DATA.json"), ClientDto[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList(clients);
    }


}