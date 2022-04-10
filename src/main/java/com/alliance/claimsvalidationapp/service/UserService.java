package com.alliance.claimsvalidationapp.service;

import com.alliance.claimsvalidationapp.entity.User;
import com.alliance.claimsvalidationapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User registerUserService(User user){

        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalStateException("Email already exist");
        }
        user.setUserStatus("active");
        return userRepository.save(user);
    }

    public User loginUserService(String email, String pass){
        User user = userRepository.findByEmailAndPassword(email, pass);
        if(userRepository.existsById(user.getId())) {
            if(!user.getUserStatus().equals("deleted"))
                return user;
        }
        throw new IllegalStateException("User not found!");
    }

    public User updateUserService(Long id, User user){
        User oldUser = null;
        if(userRepository.findById(id).isPresent()){
            oldUser = userRepository.findById(id).get();

            if((user.getFirstName() == null || user.getLastName() == null) && user.getPassword() == null){
                throw new IllegalStateException("Please fill all the fields");
            }

            if(user.getPassword() != null && (user.getFirstName() == null && user.getLastName() == null)){
                oldUser.setPassword(user.getPassword());
            }
            if (user.getFirstName() != null && user.getLastName() != null && (user.getPassword() == null)){
                oldUser.setFirstName(user.getFirstName());
                oldUser.setLastName(user.getLastName());
            }

            if (user.getFirstName() != null && user.getLastName() != null && user.getPassword() != null) {
                oldUser.setFirstName(user.getFirstName());
                oldUser.setLastName(user.getLastName());
                oldUser.setPassword(user.getPassword());
            }
        } else {
            throw new IllegalStateException("User not found");
        }
        return userRepository.save(oldUser);
    }

    public User deleteUserService(Long id){
        User oldUser = null;
        if(userRepository.findById(id).isPresent()){
            oldUser = userRepository.findById(id).get();
            oldUser.setUserStatus("deleted");
        } else {
            throw new IllegalStateException("User not found");
        }
        return userRepository.save(oldUser);
    }

    public List<User> getAllUserService(){
        return userRepository.findAll();
    }

}
