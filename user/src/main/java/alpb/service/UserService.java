package alpb.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import alpb.domain.User;
import alpb.domain.UserRepository;

public class UserService {
    @Autowired
    UserRepository userRepository;

    public Long loginUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            return 0l;
        } else {
            return user.getUserIdx();
        }
    }
}
