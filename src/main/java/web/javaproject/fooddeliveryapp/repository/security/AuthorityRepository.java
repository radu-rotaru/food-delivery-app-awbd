package web.javaproject.fooddeliveryapp.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import web.javaproject.fooddeliveryapp.model.security.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findByRole(String role);
}
