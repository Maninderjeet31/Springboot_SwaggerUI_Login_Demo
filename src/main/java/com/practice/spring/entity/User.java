package com.practice.spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String username;
    @Column(name = "password")
    @NotNull(message = "is required")
    @Size(min = 8, message = "Password should be at least 8 characters long")
    private String password;
    @Column(name = "ipaddress")
    private String IPAddress;

    public User() {
    }

    public User(String username, String password, String IPAddress) {
        this.username = username;
        this.password = password;
        this.IPAddress = IPAddress;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", IPAddress='" + IPAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(IPAddress, user.IPAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, IPAddress);
    }
}
