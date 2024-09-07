package alpb.domain;

import alpb.PaymentApplication;
import alpb.domain.PaymentApproved;
import alpb.domain.PaymentCancelled;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Payment_table")
@Data
//<<< DDD / Aggregate Root
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PostPersist
    public void onPostPersist() {
        PaymentApproved paymentApproved = new PaymentApproved(this);
        paymentApproved.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        PaymentCancelled paymentCancelled = new PaymentCancelled(this);
        paymentCancelled.publishAfterCommit();
    }

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }

    //<<< Clean Arch / Port Method
    public void paymentCancelled() {
        //implement business logic here:

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
