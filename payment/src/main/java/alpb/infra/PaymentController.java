package alpb.infra;

import alpb.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/payments")
@Transactional
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    @RequestMapping(
        value = "/payments/{id}/paymentcancelled",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Payment paymentCancelled(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /payment/paymentCancelled  called #####");
        Optional<Payment> optionalPayment = paymentRepository.findById(id);

        optionalPayment.orElseThrow(() -> new Exception("No Entity Found"));
        Payment payment = optionalPayment.get();
        payment.paymentCancelled();

        paymentRepository.save(payment);
        return payment;
    }
}
//>>> Clean Arch / Inbound Adaptor
