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
// @RequestMapping(value="/cartoons")
@Transactional
public class CartoonController {

    @Autowired
    CartoonRepository cartoonRepository;

    @RequestMapping(
        value = "/cartoons/{id}//deletecartoon",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Cartoon deleteDiary(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /cartoon/deleteDiary  called #####");
        Optional<Cartoon> optionalCartoon = cartoonRepository.findById(id);

        optionalCartoon.orElseThrow(() -> new Exception("No Entity Found"));
        Cartoon cartoon = optionalCartoon.get();
        cartoon.deleteDiary();

        cartoonRepository.save(cartoon);
        return cartoon;
    }
}
//>>> Clean Arch / Inbound Adaptor
