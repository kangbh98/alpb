package alpb.external;

import java.util.Date;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient(
//     name = "user",
//     url = "${api.url.user}",
//     fallback = ServiceImpl.class
// )
public interface UserService {
    @GetMapping(path = "/users")
    public List<User> getUser();

    @GetMapping(path = "/users/{id}")
    public User getUser(@PathVariable("id") Long id);
}
