package be.parrez.christoph.eurder.repository;

import be.parrez.christoph.eurder.model.User;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    private final Map<String, User> repository;

    public UserRepository() {
        this.repository = new HashMap<>();
    }

    public Map<String, User> getRepository() {
        return repository;
    }
}