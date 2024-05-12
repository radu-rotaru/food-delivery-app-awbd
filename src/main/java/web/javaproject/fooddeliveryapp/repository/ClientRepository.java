package web.javaproject.fooddeliveryapp.repository;

import web.javaproject.fooddeliveryapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    void deleteById(Long id);
}

