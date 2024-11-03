package ru.platypus.hw_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.platypus.hw_2.entity.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Override
    Optional<Client> findById(Long aLong);
}