package io.beagarwal.moviecatalogservice.resources;

import io.beagarwal.moviecatalogservice.models.CatalogItem;
import io.beagarwal.moviecatalogservice.models.Movie;
import io.beagarwal.moviecatalogservice.models.Rating;
import io.beagarwal.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientbuider;

    @RequestMapping("/{userID}")
    public List<CatalogItem> getCatalog(@PathVariable("userID") String userID) {

        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingdata/users/" + userID, UserRating.class);
        return ratings.getUserRating().stream().map(rating -> {
            //for each movie ID, call MovieInfo service and get details
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieID(), Movie.class);

            //put them all together
            return new CatalogItem(movie.getName(), "Description", rating.getRating());
        }).collect(Collectors.toList());

    }
}

/*
            Movie movie = webClientbuider.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieID())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();

             */