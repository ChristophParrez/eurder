package be.parrez.christoph.eurder.repository;

import be.parrez.christoph.eurder.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Map<String, User> userRepository;

    public UserRepository() {
        this.userRepository = new HashMap<>();
    }

    public void save(User user) {
        userRepository.put(user.getId(), user);
        logger.info("Saved new user to repository with id " + user.getId());
    }

    public User getEntry(String key) {
        return userRepository.get(key);
    }

    public List<User> getEntries() {
        return userRepository.values().stream().toList();
    }

}