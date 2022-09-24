package de.hsba.bi.demo6.login;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Login")
public class Login {
    @Basic
    @Getter
    @Setter
    private String userName;
    @Basic
    @Getter
    @Setter
    private String userPassword;
}
