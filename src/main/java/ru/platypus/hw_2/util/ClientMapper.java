package ru.platypus.hw_2.util;

import org.springframework.stereotype.Component;
import ru.platypus.hw_2.dto.ClientDto;
import ru.platypus.hw_2.entity.Client;

@Component
public class ClientMapper {

    public static Client toEntity(ClientDto dto) {
        return Client.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .build();
    }

    public static ClientDto toDto(Client entity) {
        return ClientDto.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .build();
    }
}
