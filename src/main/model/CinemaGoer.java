package main.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class CinemaGoer extends UserApp {

    public String phoneNumber;
    protected List<Show> hearted;
    protected List<Review> reviews;
    protected List<Show> watchList;

    public CinemaGoer(int id, String name, String address, String email, String phoneNumber){
        super(id, name, address, email);
        this.phoneNumber = phoneNumber;
        reviews = new ArrayList<>();
        watchList = new ArrayList<>();
        hearted = new ArrayList<>();
    }

    public CinemaGoer(CinemaGoer cinemaGoer){
        super(cinemaGoer.getId(), cinemaGoer.getName(), cinemaGoer.getAddress(), cinemaGoer.getEmail());
        this.phoneNumber = cinemaGoer.phoneNumber;
    }

    public CinemaGoer(int cinemaGoerId, Scanner in) throws ParseException {
        super(cinemaGoerId, in);
        this.read(in);
    }

    public void read(Scanner in) throws  ParseException{
        System.out.println("PhoneNumber : ");
        this.phoneNumber = in.nextLine();

    }

    public CinemaGoer(Scanner in) throws ParseException{
        super(in);
        this.read(in);
    }

    public CinemaGoer(int cinemaGoerId, ResultSet in) throws SQLException {
        super(cinemaGoerId, in);
        this.read(in);
    }

    public void read(ResultSet in) throws SQLException{
        this.phoneNumber = in.getString("phonenumber");
    }


    public String getPhoneNumber(){ return phoneNumber;}
    public List<Review> getReviews(){
        return reviews;
    }
    public void setReviews(List<Review> reviews){
        this.reviews = reviews;
    }
    public List<Show> getWatchList(){
        return watchList;
    }

    public void setWatchList(List<Show> shows){
        this.watchList = shows;
    }

    public void setHearted(List<Show> hearted){
        this.hearted = hearted;
    }
    public List<Show> getHearted(){
        return hearted;
    }

    @Override
    public String toCSV() {

        return "" + super.toCSV() + "," + phoneNumber;
    }

    @Override
    public String toString(){
        return super.toString() + ", phoneNumber: " + phoneNumber;
    }

}
