package be.parrez.christoph.eurder.repository;

import be.parrez.christoph.eurder.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUserId(String userId);

    List<User> findAll();

    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // private final Map<String, User> userRepository;
    //
    // public UserRepository() {
    //     this.userRepository = new HashMap<>();
    // }
    //
    // public void save(User user) {
    //     userRepository.put(user.getId(), user);
    //     logger.info("Saved new user to repository with id " + user.getId());
    // }
    //
    // public User getEntry(String key) {
    //     return userRepository.get(key);
    // }
    //
    // public List<User> getEntries() {
    //     return userRepository.values().stream().toList();
    // }

}