package de.hsba.bi.demo6.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void init() {
        userRepository.save(new User("Admin"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}