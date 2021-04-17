package io.beagarwal.ratingdataservice.resources;

import io.beagarwal.ratingdataservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingdata")
public class RatingResource {

    @RequestMapping("/{movieID}")
    public Rating getRating(@PathVariable("movieID") String movieID) {
        return new Rating(movieID, 5);
    }

}
