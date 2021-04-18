package io.beagarwal.ratingdataservice.resources;

import io.beagarwal.ratingdataservice.models.Rating;
import io.beagarwal.ratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.HTML;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingdata")
public class RatingResource {

    @RequestMapping("/{movieID}")
    public Rating getRating(@PathVariable("movieID") String movieID) {
        return new Rating(movieID, 5);
    }

    @RequestMapping("users/{userID}")
    public UserRating getUserRating(@PathVariable("userID") String userID) {
        List<Rating> ratings = Arrays.asList(
                new Rating("500", 3),
                new Rating("505", 5)
        );
        UserRating userRating = new UserRating();
        userRating.setRatings(ratings);
        return userRating;
    }
}
