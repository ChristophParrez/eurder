package be.parrez.christoph.eurder.model;

import java.util.UUID;

public class User {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final UserRole userRole;

    public User(String firstName, String lastName, String email, String phoneNumber) {
        this(firstName, lastName, email, phoneNumber, UserRole.CUSTOMER);
    }

    public User(String firstName, String lastName, String email, String phoneNumber, UserRole userRole) {
        this(UUID.randomUUID().toString(), firstName, lastName, email, phoneNumber, userRole);
    }

    public User(String userId, String firstName, String lastName, String email, String phoneNumber, UserRole userRole) {
        this.id = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
