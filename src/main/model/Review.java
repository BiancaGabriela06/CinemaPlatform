package main.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Review {
    public int id;
    public int stars;
    public String comment;
    public int cinemaGoerId;
    public int showId;

    public Review(int id, int start, String comment, int cinemaGoerId, int showId){
        this.id = id;
        this.stars = start;
        this.comment = comment;
        this.cinemaGoerId = cinemaGoerId;
        this.showId = showId;

    }

    public Review(int reviewId, Scanner in) throws ParseException {
        this.id = reviewId;
        this.read(in);
    }

    public Review(int reviewId, ResultSet in) throws SQLException {
        this.id = reviewId;
        this.read(in);
    }

    public Review(Scanner in) throws ParseException{
        System.out.println("Review's id: ");
        this.id = in.nextInt();
        System.out.println("Stars: ");
        this.stars = in.nextInt();
        System.out.println("Name of the award: ");
        this.comment = in.nextLine();
        System.out.println("Id of your account ");
        this.cinemaGoerId = in.nextInt();
        System.out.println("Show's id: ");
        this.showId = in.nextInt();
    }

    public void read(ResultSet in) throws SQLException{
        this.stars = in.getInt("stars");
        this.comment= in.getString("comment");
        this.cinemaGoerId = in.getInt("idcinemagoer");
        this.showId = in.getInt("idshow");
    }

    public void read(Scanner in) throws  ParseException{
        System.out.println("Stars: ");
        this.stars = in.nextInt();
        System.out.println("Name of the award: ");
        this.comment = in.nextLine();
        System.out.println("Id of your account ");
        this.cinemaGoerId = in.nextInt();
        System.out.println("Show's id: ");
        this.showId = in.nextInt();

    }

    public int getStars(){return stars;}
    public String getComment() {return comment;}

    public int getId() {return id;}
    public int getShowId() {return showId;}

    public int getCinemaGoerId(){
        return cinemaGoerId;
    }

    public String toCSV() {
        return "" + this.id + "," + this.stars + "," + this.comment + "," + this.cinemaGoerId +
                "," + this.showId;
    }
    public String toString(){
        return "Stars: " + stars + ", Opinion: " + comment + ", User: " + cinemaGoerId;
    }
}
