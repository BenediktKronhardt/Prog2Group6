package de.hsba.bi.demo6.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findUsers() {
        return userRepository.findByRole(User.USER_ROLE);
    }

//  User anhand des Namens finden
    public User findUserByName (String userName){
        return userRepository.findByName(userName);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}