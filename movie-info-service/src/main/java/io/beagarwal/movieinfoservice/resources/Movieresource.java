package io.beagarwal.movieinfoservice.resources;

import io.beagarwal.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class Movieresource {

    @RequestMapping("/{movieID}")
    public Movie getMovieInfo(@PathVariable("movieID") String movieID) {

        return new Movie(movieID, "Titanic");
    }
}
