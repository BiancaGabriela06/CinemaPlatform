package main.model;

/// aici o sa simulez cum ar arata interfata aplicatiei gen

import java.util.*;

public class App {
    private List<Show> shows;
    private List<Actor> actors;
    protected Set<UserApp> users ;
    public App(){
        shows = new ArrayList<>();
        actors = new ArrayList<>();
        users = new HashSet<>();
    }
    public Set<UserApp> getUsers(){
        return users;
    }

    public void setUsers(Set<UserApp> users){
        this.users = users;
    }
    public List<Show> getShows(){
        return shows;

    }
    public void setShows(List<Show> shows){

        this.shows = shows;
    }

    public List<Actor> getActors(){
        return actors;
    }

    public void setActors(List<Actor> actors){
        this.actors = actors;
    }



}


