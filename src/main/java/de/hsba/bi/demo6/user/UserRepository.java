package de.hsba.bi.demo6.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    List<User> findByRole(String role);
}


