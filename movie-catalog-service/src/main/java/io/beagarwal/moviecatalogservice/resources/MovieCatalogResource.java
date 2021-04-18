package io.beagarwal.moviecatalogservice.resources;

import com.netflix.discovery.DiscoveryClient;
import io.beagarwal.moviecatalogservice.models.CatalogItem;
import io.beagarwal.moviecatalogservice.models.Movie;
import io.beagarwal.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private WebClient.Builder webClientbuilder;

    @RequestMapping("/{userID}")
    public List<CatalogItem> getCatalog(@PathVariable("userID") String userID) {

        UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratingdata/users/" + userID, UserRating.class);
        return ratings.getUserRating().stream().map(rating -> {
            //for each movie ID, call MovieInfo service and get details
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieID(), Movie.class);

            //put them all together
            return new CatalogItem(movie.getName(), "Description", rating.getRating());
        }).collect(Collectors.toList());

    }
}
