package be.parrez.christoph.eurder.service;

import be.parrez.christoph.eurder.dto.UserDto;
import be.parrez.christoph.eurder.dto.UserRegisterDto;
import be.parrez.christoph.eurder.exceptions.BadRequestException;
import be.parrez.christoph.eurder.exceptions.UnauthorizedException;
import be.parrez.christoph.eurder.mapper.UserMapper;
import be.parrez.christoph.eurder.model.User;
import be.parrez.christoph.eurder.model.UserRole;
import be.parrez.christoph.eurder.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private static final String VALID_EMAIL_ADDRESS_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.addDummyData();
    }

    public void addDummyData() {
        User admin = new User("3ee4a38b-27a0-admin-ad21-84833182a336", "Christoph", "Parrez", "christoph.parrez@gmail.com", "Street", "1A", "9340", "Lede", "0497123456", UserRole.ADMIN);
        User user1 = new User("cd52f722-9530-user1-8fd1-184f12a75222", "John", "Doe", "john.doe@gmail.com", "Street", "10", "9300", "Aalst", "0494962154", UserRole.CUSTOMER);
        User user2 = new User("cd52f722-9530-user2-8fd1-184f12a75222", "Sam", "Smith", "sam.smith@gmail.com", "Street", "20", "1000", "Brussel", "0477963297", UserRole.CUSTOMER);
        User user3 = new User("cd52f722-9530-user3-8fd1-184f12a75222", "Joe", "Johnson", "joe.johnson@gmail.com", "Street", "30", "9000", "Gent", "046963487", UserRole.CUSTOMER);
        this.userRepository.getRepository().put(admin.getId(), admin);
        this.userRepository.getRepository().put(user1.getId(), user1);
        this.userRepository.getRepository().put(user2.getId(), user2);
        this.userRepository.getRepository().put(user3.getId(), user3);
    }

    public UserDto registerCustomer(UserRegisterDto userDto) {
        return registerUser(userDto, UserRole.CUSTOMER);
    }

    public UserDto registerUser(UserRegisterDto userDto, UserRole userRole) {
        if (!isValidEmail(userDto.getEmail()))
            throw new BadRequestException("Email " + userDto.getEmail() + " is invalid", logger);
        if (!isUniqueEmail(userDto.getEmail()))
            throw new BadRequestException("A user with the email " + userDto.getEmail() + " already exists", logger);
        User newUser = userMapper.toEntity(userDto, userRole);
        userRepository.getRepository().put(newUser.getId(), newUser);
        logger.info("Created new user with id " + newUser.getId());
        return userMapper.toDto(newUser);
    }

    public UserDto getCustomer(String authorizedId, String customerId) {
        assertUserPermissions(authorizedId, UserRole.ADMIN, "You are not authorized to get customer details.");
        User user = userRepository.getRepository().get(customerId);
        if (user == null) throw new BadRequestException("The user with id " + customerId + " could not be found.");
        return userMapper.toDto(user);
    }

    public List<UserDto> getCustomerList(String authorizedId) {
        assertUserPermissions(authorizedId, UserRole.ADMIN, "You are not authorized to get a list of customers.");
        return userMapper.toDto(getUsersByRole(UserRole.CUSTOMER));
    }

    public List<User> getUsersByRole(UserRole userRole) {
        return userRepository.getRepository().values().stream()
                .filter(user -> user.getUserRole().equals(userRole))
                .collect(Collectors.toList());
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches(VALID_EMAIL_ADDRESS_REGEX);
    }

    private boolean isUniqueEmail(String email) {
        return this.userRepository.getRepository().values().stream()
                .noneMatch(user -> user.getEmail().equals(email));
    }

    public void assertUserPermissions(String userId, UserRole userRole, String message) {
        User user = userRepository.getRepository().get(userId);
        if (userId == null || user == null || !user.getUserRole().equals(userRole)) {
            logger.warn("User with id " + userId + " did not match user role " + userRole);
            throw new UnauthorizedException(message);
        }
    }
}
