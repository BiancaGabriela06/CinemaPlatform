package main.service;
import main.model.*;

import java.util.*;

public class CinemaGoerService {

    public static void addToWatchList(CinemaGoer cinemaGoer, Show show){
        List<Show> watchList = cinemaGoer.getWatchList();
        watchList.add(show);
        cinemaGoer.setWatchList(watchList);

    }

    public static void addToHearted(CinemaGoer cinemaGoer,List<Show> shows,  String[] listNr){
        List<Show> tohearted = new ArrayList<>();
        for(String id: listNr)
            tohearted.add(shows.get(Integer.parseInt(id) - 1));
        cinemaGoer.setHearted(tohearted);
    }

    public static void recommendations(App app, CinemaGoer cinemaGoer) {
        List<Show> showsApp = app.getShows();
        List<Show> hearted = cinemaGoer.getHearted();
        List<Show> watchList = cinemaGoer.getWatchList();
        List<Show> recShows = new ArrayList<>();

        int ctMovies = 0, ctSeries = 0;
        for (Show show : hearted)
            if (show.getClass().getName() == "main.model.Movie")
                ctMovies++;
            else if (show.getClass().getName() == "main.model.Series")
                ctSeries++;
        for (Show show : watchList){
            if (show.getClass().getName() == "main.model.Movie")
                ctMovies++;
            else if (show.getClass().getName() == "main.model.Series")
                ctSeries++;
        }



        Collections.sort(showsApp, new Comparator<Show>() {
            @Override
            public int compare(Show s1, Show s2) {

                if (s1.getStars() < s2.getStars())
                    return 1;
                else
                    return -1;
            }
        });


        double percentMovie = (double) ctMovies/ (ctMovies + ctSeries);
        double percentSeries = (double) ctSeries / (ctSeries + ctMovies);


        double recMovie = percentMovie * 5;
        double recSeries = percentSeries * 5;


        for (Show show : showsApp) {

            if (show.getClass().getName() == "main.model.Movie" && hearted.contains(show) == false &&
                    watchList.contains(show) == false && recMovie != 0) {

                recMovie--;
                recShows.add(show);
            } else if (show.getClass().getName() == "main.model.Series" && hearted.contains(show) == false &&
                    watchList.contains(show) == false && recSeries != 0) {
                recSeries--;
                recShows.add(show);
            }
            if (recMovie == 0 && recSeries == 0)
                break;

        }

        System.out.println("Our recommendations are: ");
        AppService.printShows(recShows);
    }

    public static CinemaGoer copyUser(App app, String name, String address, String email, String phonenumber){
        Set<UserApp> users = app.getUsers();
        CinemaGoer cinemaGoer;
        for(UserApp user : users)
            if(user.getName().equals(name) && user.getAddress().equals(address) && user.getEmail().equals(email))
            {
                cinemaGoer = new CinemaGoer(user.getId(), name, address, email, phonenumber);
                return cinemaGoer;
            }
        return new CinemaGoer(0, "", "", "", "");
    }
    public static void giveReview(Review review, Show show){
        /*CinemaGoer cinemaGoer = review.getCinemaGoer();
        List<Review> reviewsShow = show.getReviews();
        List<Review> reviewsUser = cinemaGoer.getReviews();
        Double starsShow = show.getStars();
        if(starsShow == 0.0)
            starsShow = (double) review.getStars();
        else
        starsShow = (starsShow + review.getStars()) / 2;
        show.setStars(starsShow);
        reviewsShow.add(review);
        reviewsUser.add(review);
        cinemaGoer.setReviews(reviewsUser);
        show.setReviews(reviewsShow);*/


    }
}
