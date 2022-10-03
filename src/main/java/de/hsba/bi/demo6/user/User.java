package de.hsba.bi.demo6.user;

import de.hsba.bi.demo6.rating.Rating;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

//  Beim Speichern soll die assoziierte Einheit ebenfalls gespeichert werden
//  Wenn ein User gelöscht wird, sollen nicht die Ratings gelöscht werden
//  MappedBy: User-Objekt ist Eigentümer, Rating ist inverse Seite
    @OneToMany (cascade = CascadeType.PERSIST, orphanRemoval = false, mappedBy = "user")
    @OrderBy
    private List<Rating> ratings;

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

//  Methode "getRatings" zur Ausgabe der Bewertungen, die ein User hinzugefügt hat - falls kein Rating hinzugefügt wurde, wird eine leere ArrayList erstellt
    public List<Rating> getRatings(){
        if (ratings == null){
            ratings = new ArrayList<>();
        }
        return ratings;
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
