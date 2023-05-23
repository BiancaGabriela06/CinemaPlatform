package main.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductionCompany extends UserApp{
    protected List<Show> shows;

    public ProductionCompany(int id, String name, String address, String email){
        super(id, name, address, email);
        shows = new ArrayList<>();
    }


    public List<Show> getShows(){
        return shows;
    }
    public void setShows(List<Show> shows){
        this.shows = shows;
    }

    public void addShow(Show show){
        shows.add(show);
    }
}
