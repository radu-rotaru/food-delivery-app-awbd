package web.javaproject.fooddeliveryapp.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import web.javaproject.fooddeliveryapp.model.security.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
