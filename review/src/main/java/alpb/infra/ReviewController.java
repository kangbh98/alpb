package alpb.infra;

import alpb.domain.*;

import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/reviews")
@Transactional
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ReviewService reviewService;

    @RequestMapping(
        value = "/{id}//deletereview",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Review deleteReview(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /review/deleteReview  called #####");
        Optional<Review> optionalReview = reviewRepository.findById(id);

        optionalReview.orElseThrow(() -> new Exception("No Entity Found"));
        Review review = optionalReview.get();
        review.deleteReview();

        reviewRepository.save(review);
        return review;
    }

    @GetMapping("/{placeName}")
    public ResponseEntity<Map<String, Object>> getReview(@PathVariable String placeName) {
        System.out.println("place 옴");
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            List<Review> reviews = reviewService.getReviews(placeName);
            status = HttpStatus.OK;
            resultMap.put("reviewList", reviews);
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @PostMapping("/write")
    public ResponseEntity<Map<String, Object>> writeReview(@RequestBody Review review) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.CREATED;
        try {
            reviewService.writeReview(review);
            status = HttpStatus.OK;
            resultMap.put("status", status);
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
//>>> Clean Arch / Inbound Adaptor

