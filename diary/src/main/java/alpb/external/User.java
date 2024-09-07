package alpb.external;

import java.util.Date;
import lombok.Data;

@Data
public class User {

    private Long userIdx;
    private String email;
    private String password;
    private String nickname;
    private String profileImg;
    private String refreshToken;
    private Integer credit;
}
