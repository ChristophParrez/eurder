package be.parrez.christoph.eurder.dto;

public class UserRegisterDto {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;

    public UserRegisterDto(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
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
}
