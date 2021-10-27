package be.parrez.christoph.eurder.dto;

public class UserDto {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String street;
    private final String houseNumber;
    private final String postalCode;
    private final String city;
    private final String phoneNumber;

    public UserDto(String id, String firstName, String lastName, String email, String street, String houseNumber, String postalCode, String city, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
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
}
