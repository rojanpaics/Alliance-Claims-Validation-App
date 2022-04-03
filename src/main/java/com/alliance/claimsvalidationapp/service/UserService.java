package com.alliance.claimsvalidationapp.service;

import com.alliance.claimsvalidationapp.entity.User;
import com.alliance.claimsvalidationapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User registerUserService(User user){
        User tempUser = userRepository.findByEmail(user.getEmail()).get();

        if(userRepository.existsById(tempUser.getId())){
            throw new IllegalStateException("Email already exist");
        }
        return userRepository.save(user);
    }
}
