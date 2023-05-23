package main.model;

import java.util.ArrayList;
import java.util.List;

public class Show {
    public long id;
    private String name;
    ProductionCompany productionCompany;

    public double stars;
    private List<Actor> actors;
    private List<Review> reviews;

    public Show(){

    }
    public Show(long id, String name, ProductionCompany productionCompany, List<Actor> actors){
        this.id = id;
        this.name = name;
        this.productionCompany = productionCompany;
        this.actors = actors;
        this.stars = 0;
        reviews = new ArrayList<>();
    }

    public Show(Show show){
        this.id = show.id;
        this.name = show.name;
        this.productionCompany = show.productionCompany;
        this.stars = show.stars;
        this.actors = show.actors;
        this.reviews = show.reviews;
    }
    public long getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public double getStars(){ return stars;}
    public void setStars(double stars) {this.stars = stars;}

    public List<Review> getReviews(){
        return reviews;
    }

    public void setReviews(List<Review> reviews){
        this.reviews= reviews;
    }

    public String toString(){
        return "Name of the Show: " + name + ", Production Company: " + productionCompany.getName();
    }



}
