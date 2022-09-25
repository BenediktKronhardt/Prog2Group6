package de.hsba.bi.demo6.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name="UserTest")
public class User {

    @Getter
    @Id
    @GeneratedValue (generator = "USER_ID")
    private Long id;

    @Getter
    @Setter
    private String name;

    public User(String name) {
        this.name = name;
    }

//    @Override
//    public int compareTo(User other) {
//        return this.name.compareTo(other.name);
//    }

    @Override
    public String toString() {
        return name;
    }
}
