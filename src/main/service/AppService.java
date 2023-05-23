package main.service;

import main.model.*;


import java.util.*;

public class AppService {

    public static void initializeApp(App app){
        /*List<Actor> actors1 = new ArrayList<>();
        List<Actor> actors2 = new ArrayList<>();
        List<Actor> actors3 = new ArrayList<>();
        List<Actor> actors4 = new ArrayList<>();
        List<Actor> actors5 = new ArrayList<>();
        List<Actor> actors6 = new ArrayList<>();
        List<Actor> actors7 = new ArrayList<>();
        List<Actor> actors8 = new ArrayList<>();

        Actor actor1 = new Actor(1, "Alejandro", 25);
        Actor actor2 = new Actor(2, "Brad Pitt", 52);
        Actor actor3 = new Actor(3, "Margot Robbie", 32);
        Actor actor4 = new Actor(4, "Tom Hanks", 58);
        Actor actor5 = new Actor(5, "Morgan Freeman", 85);
        Actor actor6 = new Actor(6, "Jim Carrey", 45);
        Actor actor7 = new Actor(7, "Sandra Bullock", 38);
        Actor actor8 = new Actor(8, "Scarlett Johansson", 35);



        List<Award> awards1 = new ArrayList<>();
        List<Award> awards2 = new ArrayList<>();
        List<Award> awards3= new ArrayList<>();
        List<Award> awards4 = new ArrayList<>();
        List<Award> awards5 = new ArrayList<>();
        List<Award> awards6 = new ArrayList<>();
        awards1.add(award3);
        awards1.add(award1);
        awards1.add(award4);
        awards1.add(award5);
        awards2.add(award1);
        awards2.add(award7);
        awards3.add(award3);
        awards3.add(award6);
        awards4.add(award2);
        awards4.add(award5);
        awards5.add(award6);
        awards6.add(award1);
        awards6.add(award5);
        awards6.add(award4);

        actor1.setAwards(awards1);
        actor2.setAwards(awards6);
        actor3.setAwards(awards3);
        actor4.setAwards(awards5);
        actor5.setAwards(awards2);
        actor4.setAwards(awards4);
        actor5.setAwards(awards3);
        actor6.setAwards(awards5);
        actor7.setAwards(awards1);
        actor8.setAwards(awards2);


        List<Actor> actorsApp = new ArrayList<>();
        actorsApp.add(actor1);
        actorsApp.add(actor2);
        actorsApp.add(actor3);
        actorsApp.add(actor4);
        actorsApp.add(actor5);
        actorsApp.add(actor6);
        actorsApp.add(actor7);
        actorsApp.add(actor8);

        app.setActors(actorsApp);

        actors1.add(actor1);
        actors1.add(actor3);
        actors1.add(actor7);
        actors2.add(actor2);
        actors2.add(actor1);
        actors2.add(actor8);
        actors3.add(actor3);
        actors3.add(actor6);
        actors4.add(actor1);
        actors4.add(actor8);
        actors5.add(actor5);
        actors5.add(actor7);
        actors5.add(actor8);
        actors6.add(actor1);
        actors6.add(actor4);
        actors7.add(actor4);
        actors8.add(actor8);
        actors8.add(actor1);

        ProductionCompany company1 = new ProductionCompany(1, "Paramount Pictures", "USA, LA", "paramountpictures@paramount.com");
        Movie movie1 = new Movie(1, "Top Gun", company1, actors1, "2h30'" );
        Movie movie2 = new Movie(2, "Babylon", company1, actors2, "1h45'");
        Movie movie3 = new Movie(3, "Babylon 2", company1, actors3, "1h30'");
        Series series1 = new Series(4, "Modern Family", company1, actors4, 12, 1024);
        Series series2 = new Series(5, "Sex and the City", company1, actors7, 7, 140);
        Series series3 = new Series(6, "Friends", company1, actors5, 11, 948);
        Series series4 = new Series(7, "The Office", company1, actors6, 8, 600);
        Series series5 = new Series(8, "Hannah Montana", company1, actors8, 20, 200);
        Movie movie4 = new Movie(9, "Hunger Games", company1, actors5, "3h10'");

        company1.addShow(movie1);
        company1.addShow(movie2);
        company1.addShow(movie3);
        company1.addShow(series1);
        company1.addShow(series2);
        company1.addShow(movie4);
        company1.addShow(series3);
        company1.addShow(series5);
        company1.addShow(series4);


        AppService.addShowtoApp(app, movie1);
        AppService.addShowtoApp(app, movie2);
        AppService.addShowtoApp(app, movie3);
        AppService.addShowtoApp(app, movie4);
        AppService.addShowtoApp(app, series1);
        AppService.addShowtoApp(app, series2);
        AppService.addShowtoApp(app, series3);
        AppService.addShowtoApp(app, series4);
        AppService.addShowtoApp(app, series5);
        */
        Set<UserApp> users = new HashSet<>();
        CinemaGoer cinemaGoer = new CinemaGoer(1, "gizzehhh", "Romania, Bucuresti", "bianca@yahoo.com", "0771002933");
        users.add(cinemaGoer);
        app.setUsers(users);
    }

    public static boolean searchUser(App app, String name, String address, String email){
        Set<UserApp> users = app.getUsers();
        for(UserApp user : users){
            if(user.getName().equals(name) && user.getAddress().equals(address) && user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    public static ProductionCompany createAccount(int id, String name, String address, String email){
        return new ProductionCompany(id, name, address, email);
    }

    public static void showProfile(UserApp user){
        System.out.println(user);
    }

    public static void printShows(List<Show> shows){

        for(Show s: shows){
            if(s != null)
                System.out.println(s);
        }
    }

    public static List<Show> ShowsInOrder(App app){
        List<Show> showstoOrder = app.getShows();
        List<Show> showsInOrder = new ArrayList<>();
        Collections.sort(showstoOrder, new Comparator<Show>(){
            @Override
            public int compare(Show s1, Show s2){
                return s1.getName().compareTo(s2.getName());
            }
        });

        for(Show s: showstoOrder)
            if (s != null)
                showsInOrder.add(s);

        return showsInOrder;

    }




    public List<Show> searchShow(App app, String searchedShow) {
        List<Show> result = new ArrayList<>();
        for (Show s : app.getShows()) {

            String name = s.getName();
            if (name.toUpperCase().contains(searchedShow.toUpperCase())) {
                result.add(s);
            }

        }
        return result;
    }

    public static void addShowtoApp(App app, Show show){
        List<Show> showsApp = app.getShows();
        showsApp.add(show);
        app.setShows(showsApp);
    }

    public static void printActors(App app){
        for(Actor a: app.getActors()) {
            System.out.print(a.getId());
            System.out.println(" " + a);
        }
    }
    public static void awardedActors(App app){
        List<Actor> bestActors = new ArrayList<>();
        List <Actor> actorsApp = app.getActors();
        Collections.sort(actorsApp, new Comparator<Actor>(){
            @Override
            public int compare(Actor a1, Actor a2){
                int nraward1 = a1.getAwards().size();
                int nraward2 = a2.getAwards().size();
                if(nraward1 < nraward2)
                    return 1;
                else
                    return -1;
            }
        });

       int counter = -1;
       for(Actor a: actorsApp)
       {
           bestActors.add(a);
           counter ++;
           if(counter == 4)
               break;
       }

       for(Actor a : bestActors)
       {
           System.out.print(a + " ");
           System.out.print(", Number of awards: " + a.getAwards().size());
           System.out.println();
       }

    }


    public static void bestReviews(App app){
        List<Show> appShows = app.getShows();
        Collections.sort(appShows, new Comparator<Show>(){
            @Override
            public int compare(Show s1, Show s2){

                if(s1.getStars() < s2.getStars())
                    return 1;
                else
                    return -1;
            }
        });
        for(Show s : appShows) {
            System.out.print(s);
            System.out.print(" Stars: " + s.getStars());
            System.out.println();
        }

    }


}
