package com.example.modashop.service;

import com.example.modashop.entity.User;
import com.example.modashop.exception.EntityNotFoundException;
import com.example.modashop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User createUser(User user) {
        if (repository.existsByEmail(user.getEmail()))
            throw  new RuntimeException("Email already existed");
        return repository.save(user);
    }

    public User updateUser(User newUser, Long id) {
        return repository.findById(id)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    user.setFullName(newUser.getFullName());
                    user.setAddress(newUser.getAddress());
                    user.setPhone(newUser.getPhone());
                    return repository.save(user);
                })
                .orElseThrow( () -> new EntityNotFoundException(User.class, id));
    }

    public String deleteUser(@PathVariable Long id){
        if(!repository.existsById(id))
            throw new EntityNotFoundException(User.class, id);
        repository.deleteById(id);
        return "User with id "+ id +" has been deleted";
    }

    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException(User.class, id));
    }
}