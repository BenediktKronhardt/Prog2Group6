package de.hsba.bi.demo6.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

// Schnittstelle zwischen UserAdapter und -Repository
@Service
@Transactional
@RequiredArgsConstructor
public class UserAdapterService implements UserDetailsService {

    private final UserRepository repository;

// Schaut ob ein Nutzername vorhanden ist oder nicht. Wenn nicht vorhanden wird eine UserNotFoundException geworfen
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserAdapter(user);
    }
}
