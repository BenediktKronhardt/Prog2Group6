package de.hsba.bi.demo6.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
// Name ist nicht "User", da dadurch ein Fehler mit der Datenbank entsteht
@Table(name = "UserTable")
public class User implements Comparable<User>{

// legt die Rolle User an (keine Rechte)
    public static String USER_ROLE = "USER";
// legt die Rolle Admin an (alle Rechte)
    public static String ADMIN_ROLE = "ADMIN";

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue (generator = "USER_ID")
    private Long id;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String password;

    private String role;

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

//Überprüft ob ein Nutzername schon in Verwendung ist
    @Override
    public int compareTo(User other) {
        return this.name.compareTo(other.name);
   }

// Wenn User ausgegeben wird, wird nur der Name angezeigt
    @Override
    public String toString() {
        return name;
    }
}
