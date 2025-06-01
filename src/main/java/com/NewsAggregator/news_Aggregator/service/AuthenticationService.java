package com.NewsAggregator.news_Aggregator.service;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.NewsAggregator.news_Aggregator.Entity.Users;
import com.NewsAggregator.news_Aggregator.dto.userdto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.NewsAggregator.news_Aggregator.repository.UserRepository;


import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Collections;
import java.util.Optional;


@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    @PostConstruct
    public void init() {
        System.out.println("PasswordEncoder: " + _passwordEncoder); // Should NOT be null
    }

    public Users registerUser(userdto user) {
        Users dbUser = new Users();
        dbUser.setUsername(user.getUsername());
        dbUser.setPassword(_passwordEncoder.encode(user.getPassword()));
        dbUser.setEnabled(false);
        dbUser.setRole("USER");
        return userRepository.save(dbUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userO =userRepository.findByUsername(username);
        if(userO.isEmpty()){

            throw new UsernameNotFoundException("User not found with username: " + username);

        }
        Users user = userO.get();
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new org.springframework.security.core.authority.SimpleGrantedAuthority(user.getRole()))
        );
    }



}
