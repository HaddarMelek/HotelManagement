package tn.hotelmanagement.model;

import jakarta.persistence.*;

import java.util.List;

@Entity

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 128)
    private String username;

    @Column(nullable = false, length = 128)
    private String password;

    @Column(nullable = false, unique = true, length = 128)
    private String email;

    @Column(nullable = false, length = 32)
    private String role;
    @ElementCollection
    @CollectionTable(
            name = "account_hotels",
            joinColumns = @JoinColumn(name = "account_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"account_id", "hotel_id"})
    )
    @Column(name = "hotel_id", nullable = false)
    private List<Long> hotelsId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Long> getHotelsId() {
        return hotelsId;
    }

    public void setHotelsId(List<Long> hotelsId) {
        this.hotelsId = hotelsId;
    }


}
