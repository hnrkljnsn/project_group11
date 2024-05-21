package no.ntnu.backend;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int userId;
    public String username;
    public String password;
    /*
    public boolean admin; (?)
     */

    @OneToMany
    private List<Flight> favoriteFlights;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Flight> getFavoriteFlights() {
        return favoriteFlights;
    }

    public void setFavoriteFlights(List<Flight> favoriteFlights) {
        this.favoriteFlights = favoriteFlights;
    }
}

