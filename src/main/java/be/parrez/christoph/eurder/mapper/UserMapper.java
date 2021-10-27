package be.parrez.christoph.eurder.mapper;

import be.parrez.christoph.eurder.dto.UserDto;
import be.parrez.christoph.eurder.dto.UserRegisterDto;
import be.parrez.christoph.eurder.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper {
    public User toEntity(UserRegisterDto dto) {
        return new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getStreet(), dto.getHouseNumber(), dto.getPostalCode(), dto.getCity(), dto.getPhoneNumber());
    }

    public List<UserDto> toDto(List<User> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    public UserDto toDto(User entity) {
        return new UserDto(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getStreet(), entity.getHouseNumber(), entity.getPostalCode(), entity.getCity(), entity.getPhoneNumber());
    }
}
