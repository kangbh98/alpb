package alpb.infra;

import alpb.domain.*;
import alpb.service.UserService;
import alpb.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/users")
@Transactional
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody User user) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            long userIdx = userService.loginUser(user.getEmail(), user.getPassword());
            if (userIdx != 0) {
                String accessToken = jwtUtil.createAccessToken(userIdx);
                String refreshToken = jwtUtil.createRefreshToken(userIdx);

                // userService.saveRefreshToken(userIdx, refreshToken);

                resultMap.put("access-token", accessToken);
                resultMap.put("refresh-token", refreshToken);

                status = HttpStatus.CREATED;
            } else {
                resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
                status = HttpStatus.UNAUTHORIZED;
            }
        } catch (Exception e) { // 기타 예외
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/info/{userIdx}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable("userIdx") Long userIdx,
            HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        // 로그인 사용자 정보.
        User user = userService.getUserByUserIdx(userIdx);
        resultMap.put("userInfo", user);
        status = HttpStatus.OK;

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(User user) {
        userService.deleteUser(user.getUserIdx());
        return ResponseEntity.status(HttpStatus.OK).body("회원가입 성공");
    }

}
// >>> Clean Arch / Inbound Adaptor
