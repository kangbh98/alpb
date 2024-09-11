package alpb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alpb.domain.User;
import alpb.domain.UserRepository;

@Service
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

    public User getUserByUserIdx(Long userIdx) {
        return userRepository.findByUserIdx(userIdx);
    }

    public void deleteUser(Long userIdx) {
        userRepository.deleteByUserIdx(userIdx);
    }

}
