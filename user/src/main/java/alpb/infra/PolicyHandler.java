package alpb.infra;

import alpb.config.kafka.KafkaProcessor;
import alpb.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    UserRepository userRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PaymentApproved'"
    )
    public void wheneverPaymentApproved_CreditIncrease(
        @Payload PaymentApproved paymentApproved
    ) {
        PaymentApproved event = paymentApproved;
        System.out.println(
            "\n\n##### listener CreditIncrease : " + paymentApproved + "\n\n"
        );

        // Sample Logic //
        User.creditIncrease(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DiaryCreated'"
    )
    public void wheneverDiaryCreated_CreditDecrease(
        @Payload DiaryCreated diaryCreated
    ) {
        DiaryCreated event = diaryCreated;
        System.out.println(
            "\n\n##### listener CreditDecrease : " + diaryCreated + "\n\n"
        );

        // Sample Logic //
        User.creditDecrease(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
