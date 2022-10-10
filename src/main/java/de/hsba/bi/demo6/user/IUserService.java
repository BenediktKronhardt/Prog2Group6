package de.hsba.bi.demo6.user;

// Dient dazu einen neuen Nutzer zu registrieren
public interface IUserService {
    User registerNewUserAccount(UserDto userDto);
}
