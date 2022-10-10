package de.hsba.bi.demo6.UserIntegrationTest;


import de.hsba.bi.demo6.user.User;
import de.hsba.bi.demo6.user.UserRepository;
import de.hsba.bi.demo6.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userServiceTest;

    @BeforeEach
    void delete(){
        userRepository.deleteAll();
    }

    @Test
    void shouldFindAllUser(){

        //given, Neue User werden gesetzt in das leere Repo
        User mikail = new User("Mikail", "123456", User.USER_ROLE);
        User bene = new User("Bene", "123456", User.USER_ROLE);
        User victor = new User("Victor", "123456", User.USER_ROLE);
        User nadja = new User("Nadja", "123456", User.USER_ROLE);
        userRepository.save(mikail);
        userRepository.save(bene);
        userRepository.save(victor);
        userRepository.save(nadja);

        //when, Alle gesetzten User werden aufgerufen
        List<User> users = userServiceTest.findAll();

        //then, Überprüfen, ob genau diese User in der Liste vorhanden sind
        assertThat(users).containsExactly(mikail, bene, victor, nadja);

    }

    @Test
    void shouldFindByRole(){

        //when, selbes Prinzip wie oben, nur mit anderen Rollen
        User mikail = new User("Mikail", "123456", User.USER_ROLE);
        User bene = new User("Bene", "123456", User.USER_ROLE);
        User victor = new User("Victor", "123456", User.ADMIN_ROLE);
        User nadja = new User("Nadja", "123456", User.ADMIN_ROLE);
        userRepository.save(mikail);
        userRepository.save(bene);
        userRepository.save(victor);
        userRepository.save(nadja);

        //when, Alle gesetzten User werden aufgerufen
        List<User> users = userServiceTest.findUsers();

        //then, Überprüfen, ob genau diese User in der Liste vorhanden sind, in an Betracht der Rolle
        assertThat(users).containsExactly(mikail, bene);

    }
}
