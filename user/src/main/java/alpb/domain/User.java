package alpb.domain;

import alpb.UserApplication;
import alpb.domain.CreditDecreased;
import alpb.domain.CreditIncreased;
import alpb.domain.UserLoggedIn;
import alpb.domain.UserLoggedOut;
import alpb.domain.UserSignedUp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;

@Entity
@Table(name = "User_table")
@Data
//<<< DDD / Aggregate Root
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userIdx;

    private String email;

    private String password;

    private String nickname;

    private String profileImg;

    private String refreshToken;

    private Integer credit;

    @PostPersist
    public void onPostPersist() {
        UserSignedUp userSignedUp = new UserSignedUp(this);
        userSignedUp.publishAfterCommit();

        UserLoggedIn userLoggedIn = new UserLoggedIn(this);
        userLoggedIn.publishAfterCommit();

        UserLoggedOut userLoggedOut = new UserLoggedOut(this);
        userLoggedOut.publishAfterCommit();

        CreditIncreased creditIncreased = new CreditIncreased(this);
        creditIncreased.publishAfterCommit();

        CreditDecreased creditDecreased = new CreditDecreased(this);
        creditDecreased.publishAfterCommit();
    }

    public static UserRepository repository() {
        UserRepository userRepository = UserApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }

    //<<< Clean Arch / Port Method
    public static void creditIncrease(PaymentApproved paymentApproved) {
           
        User user = repository().findByUserIdx(paymentApproved.getUserId());
        if (user != null) {
            user.setCredit(user.getCredit() + paymentApproved.getProduct());
            repository().save(user);
        }
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    @Transactional
    public static void creditDecrease(DiaryCreated diaryCreated) {

        User user = repository().findByUserIdx(diaryCreated.getUserIdx());
        if (user != null) {
            user.setCredit(user.getCredit() - 1);
            repository().save(user);
        }
    }
}

