package de.hsba.bi.demo6.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findUsers() {
        return userRepository.findByRole(User.USER_ROLE);
    }

    public User save(User user) {
        return userRepository.save(user);
    }


    @Override
    public User registerNewUserAccount(UserDto userDto){
        User user = new User(userDto.getName(), passwordEncoder.encode(userDto.getPassword()), "USER");
        return userRepository.save(user);
    }
}