package alpb.infra;

import java.util.List;

import alpb.domain.Review;
import alpb.domain.ReviewRepository;

public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviews(String placeName) {
        return reviewRepository.findAllByPlaceName(placeName);
    }

    public void writeReview(Review review) {
        review.setTotalScore(
                (int) Math.round((review.getFoodScore() + review.getTrafficScore() + review.getTravelScore()) / 3)
        );
        reviewRepository.save(review);
    }
}
