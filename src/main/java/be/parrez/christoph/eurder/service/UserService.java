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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.Nullable;
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
        userRepository.save(newUser);
        return userMapper.toDto(newUser);
    }

    public UserDto getCustomer(String authorizedId, String customerId) {
        assertUserPermissions(authorizedId, UserRole.ADMIN, "You are not authorized to get customer details.");
        User user = userRepository.findByUserId(customerId);
        if (user == null) throw new BadRequestException("The user with id " + customerId + " could not be found.");
        return userMapper.toDto(user);
    }

    public List<UserDto> getCustomerList(String authorizedId) {
        assertUserPermissions(authorizedId, UserRole.ADMIN, "You are not authorized to get a list of customers.");
        return userMapper.toDto(getUsersByRole(UserRole.CUSTOMER));
    }

    public List<User> getUsersByRole(UserRole userRole) {
        return userRepository.findAll().stream()
                .filter(user -> user.getUserRole().equals(userRole))
                .collect(Collectors.toList());
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches(VALID_EMAIL_ADDRESS_REGEX);
    }

    private boolean isUniqueEmail(String email) {
        return userRepository.findAll().stream()
                .noneMatch(user -> user.getEmail().equals(email));
    }

    public User assertUserPermissions(String userId, List<UserRole> userRoles, String message) {
        User user = userRepository.findByUserId(userId);
        if (userId == null || user == null || userRoles.stream().noneMatch(role -> user.getUserRole().equals(role))) {
            logger.warn("User with id " + userId + " did not match user role " + userRoles.stream().map(Enum::toString).collect(Collectors.joining(", ")));
            throw new UnauthorizedException(message);
        }
        return user;
    }

    public void assertUserPermissions(String userId, UserRole userRole, String message) {
        assertUserPermissions(userId, List.of(userRole), message);
    }
}
