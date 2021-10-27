package be.parrez.christoph.eurder.model;

import java.util.UUID;

public class User {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String street;
    private final String houseNumber;
    private final String postalCode;
    private final String city;
    private final String phoneNumber;
    private final UserRole userRole;

    public User(String firstName, String lastName, String email, String street, String houseNumber, String postalCode, String city, String phoneNumber) {
        this(firstName, lastName, email, street, houseNumber, postalCode, city, phoneNumber, UserRole.CUSTOMER);
    }

    public User(String firstName, String lastName, String email, String street, String houseNumber, String postalCode, String city, String phoneNumber, UserRole userRole) {
        this(UUID.randomUUID().toString(), firstName, lastName, email, street, houseNumber, postalCode, city, phoneNumber, userRole);
    }

    public User(String userId, String firstName, String lastName, String email, String street, String houseNumber, String postalCode, String city, String phoneNumber, UserRole userRole) {
        this.id = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
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

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
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
