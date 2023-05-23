package main.model;

import java.util.List;

public class Movie extends Show{
    private final String duration;

    public Movie(long id, String name, ProductionCompany productionCompany, List<Actor> actors, String duration){
        super(id, name, productionCompany, actors);
        this.duration = duration;
    }

    public Movie(Movie movie){
        super(movie);
        this.duration = movie.duration;
    }
    @Override
    public String toString(){
        return super.toString() + ", duration of the show: " + duration;

    }
}
