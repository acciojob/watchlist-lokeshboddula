package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    HashMap<String, Movie> movieDB = new HashMap<>();
    HashMap<String, Director> directorDB = new HashMap<>();
    HashMap<String, String> movieDirectorPair = new HashMap<>();
    public String addMovie(Movie movie) {
        movieDB.put(movie.getName(), movie);
        return "movie added Successfully!";
    }

    public String addDirector(Director director) {
        directorDB.put(director.getName(), director);
        return "director added Successfully!";
    }
    public String addMovieDirectorPair(String movieName, String directorName) {
        movieDirectorPair.put(movieName, directorName);
        return "movie-director pair added";
    }
    public Movie getMovieByName(String movieName) {
        return movieDB.getOrDefault(movieName, null);
    }

    public Director getDirectorByName(String directorName) {

        return directorDB.getOrDefault(directorName, null);
    }

    public List<Movie> getMoviesByDirectorName(String directorName) {
        List<Movie> movieList = new ArrayList<>();
        for(Map.Entry<String, String> entry : movieDirectorPair.entrySet()) {
            if(entry.getValue().equals(directorName)) {
                movieList.add(movieDB.get(entry.getKey()));
            }
        }
        return movieList;
    }
    public List<Movie> findAllMovies() {
        return new ArrayList<>(movieDB.values());
    }

    public String deleteDirectorByName(String directorName) {
        directorDB.remove(directorName);
//        movieDirectorPair.remove(directorName);
        for(Map.Entry<String, String> entry : movieDirectorPair.entrySet()) {
            if(entry.getValue().equals(directorName)) {
                String movieName = entry.getKey();
                movieDB.remove(movieName);
                movieDirectorPair.remove(movieName);
            }
        }
        return "Director removed Successfully";
    }


    public String deleteAllDirectors() {
        for(String directorName : directorDB.keySet()) {
            directorDB.remove(directorName);
            for(Map.Entry<String, String> entry : movieDirectorPair.entrySet()) {
                if(entry.getValue().equals(directorName)) {
                    String movieName = entry.getKey();
                    movieDB.remove(movieName);
                    movieDirectorPair.remove(movieName);
                }
            }
        }
        return "all directors deleted";
    }


}
