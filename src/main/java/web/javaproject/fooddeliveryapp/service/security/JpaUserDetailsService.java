package web.javaproject.fooddeliveryapp.service.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import web.javaproject.fooddeliveryapp.model.security.CustomUserDetails;
import web.javaproject.fooddeliveryapp.repository.security.UserRepository;
import web.javaproject.fooddeliveryapp.model.security.User;
import web.javaproject.fooddeliveryapp.model.security.Authority;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile("mysql")
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;

        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent())
            user = userOpt.get();
        else
            throw new UsernameNotFoundException("Username: " + username);

        log.info(user.toString());

        return new CustomUserDetails(user.getUsername(),
                user.getPassword(), user.getAssociatedId(), user.getEnabled(), user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),user.getAccountNonLocked(),
                getAuthorities(user.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Authority> authorities) {
        if (authorities == null){
            return new HashSet<>();
        } else if (authorities.size() == 0){
            return new HashSet<>();
        }
        else{
            return authorities.stream()
                    .map(Authority::getRole)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        }
    }
}
