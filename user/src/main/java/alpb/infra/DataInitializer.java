package alpb.infra;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import alpb.domain.User;
import alpb.domain.UserRepository;

@Component
public class DataInitializer {
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        User user = new User();
        user.setUserIdx(1L);
        user.setCredit(10);
        user.setEmail("fund88@naver.com");
        user.setNickname("강병헌");
        user.setPassword("password");
        user.setProfileImg("https://github.com/UMCCMAP/server/assets/89764169/74577690-2d2d-4491-a3b8-319d8a947981");
        user.setRefreshToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiaWF0IjoxNzI1NDQ3ODcyLCJleHAiOjE3MjU0NDc5MTIsInVzZXJJZCI6MX0.UuptM96_IuFPHEuZ-hki0VClD-xjoLhQ-NGH0vuFbSM");

        userRepository.save(user);
    }
}
