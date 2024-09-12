package alpb.infra;

import alpb.domain.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
@RequestMapping(value="/cartoons")
@Transactional
public class CartoonController {

    @Autowired
    CartoonRepository cartoonRepository;
    @Autowired
    CartoonService cartoonService;

    @PostMapping("/create/diary")
    public String postImage(@RequestBody Map<String, Object> requestData) {
        Long userIdx = Long.valueOf(requestData.get("userIdx").toString());
        Date date = Date.valueOf(requestData.get("date").toString());
        String comment = requestData.get("comment").toString();
    
        String s = cartoonService.makePrompt(comment);
        String imageUrl = cartoonService.openAiImageUrl(s);
        Cartoon cartoon = new Cartoon(date, userIdx, imageUrl, comment);
        cartoonService.createCartoon(cartoon);
        return imageUrl;
    }

    @GetMapping("/get/diary")
    public Cartoon getCartoon(@RequestParam Long userIdx, @RequestParam Date date) {
        return cartoonService.getCartoon(userIdx, date);
    }



    @RequestMapping(
        value = "/{id}//deletecartoon",
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
