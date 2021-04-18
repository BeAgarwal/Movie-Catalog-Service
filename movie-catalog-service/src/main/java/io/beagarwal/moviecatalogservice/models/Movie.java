package io.beagarwal.moviecatalogservice.models;

public class Movie {

    private String movieID;
    private String name;
    private String description;

    public Movie() {

    }

    public Movie(String movieID, String name, String description) {
            this.movieID = movieID;
            this.name = name;
            this.description = description;
        }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
