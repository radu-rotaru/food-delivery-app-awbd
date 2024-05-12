package web.javaproject.fooddeliveryapp.bootstrap;

import web.javaproject.fooddeliveryapp.model.security.Authority;
import web.javaproject.fooddeliveryapp.model.security.User;
import web.javaproject.fooddeliveryapp.repository.security.AuthorityRepository;
import web.javaproject.fooddeliveryapp.repository.security.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Profile("mysql")
public class DataLoader implements CommandLineRunner {
    private AuthorityRepository authorityRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    private void loadUserData() {
        if (userRepository.count() == 0){
            Authority adminRole = authorityRepository.save(Authority.builder().role("ADMIN").build());
            Authority clientRole = authorityRepository.save(Authority.builder().role("CLIENT").build());
            Authority restaurantRole = authorityRepository.save(Authority.builder().role("RESTAURANT").build());
            Authority courierRole = authorityRepository.save(Authority.builder().role("COURIER").build());

           User admin = User.builder()
                   .username("admin")
                   .password(passwordEncoder.encode("12345"))
                   .authority(adminRole)
                   .build();

            User client = User.builder()
                    .username("client")
                    .password(passwordEncoder.encode("12345"))
                    .associatedId(1L)
                    .authority(clientRole)
                    .build();

            User restaurant = User.builder()
                    .username("restaurant")
                    .password(passwordEncoder.encode("12345"))
                    .associatedId(1L)
                    .authority(restaurantRole)
                    .build();


          User courier = User.builder()
                  .username("courier")
                  .password(passwordEncoder.encode("12345"))
                  .associatedId(1L)
                  .authority(courierRole)
                  .build();

           userRepository.save(admin);
           userRepository.save(client);
           userRepository.save(restaurant);
           userRepository.save(courier);
        }
    }


    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }
}
