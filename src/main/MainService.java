package main;

import main.model.*;
import main.repository.ActorRepository.ActorDatabase;
import main.repository.AwardRepository.AwardDatabase;
import main.repository.CinemaGoerRepository.CinemaGoerDatabase;
import main.repository.CinemaGoerRepository.CinemaGoerFactory;
import main.repository.ReviewRepository.ReviewDatabase;
import main.repository.ReviewRepository.ReviewFactory;
import main.repository.UserAppRepository.UserAppDatabase;
import main.repository.UserAppRepository.UserAppFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainService {

    private ActorDatabase actorDatabase = null;
    private CinemaGoerDatabase cinemaGoerDatabase = null;
    private AwardDatabase awardDatabase = null;
    private ReviewDatabase reviewDatabase = null;

    private UserAppDatabase userAppDatabase = null;

    private List<Actor> actors = new ArrayList<>();
    private List<CinemaGoer> cinemaGoers = new ArrayList<>();
    private List<Award> awards = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();
    private List<UserApp> users = new ArrayList<>();

    public MainService(ActorDatabase actorDatabase, CinemaGoerDatabase cinemaGoerDatabase,
                       AwardDatabase awardDatabase, ReviewDatabase reviewDatabase, UserAppDatabase userAppDatabase){

        this.actorDatabase = actorDatabase;
        this.actors = actorDatabase.read();

        this.awardDatabase = awardDatabase;
        this.awards = awardDatabase.read();

        this.cinemaGoerDatabase = cinemaGoerDatabase;
        this.cinemaGoers = cinemaGoerDatabase.read();

        this.reviewDatabase = reviewDatabase;
        this.reviews = reviewDatabase.read();

        this.userAppDatabase = userAppDatabase;
        this.users = userAppDatabase.read();

    }

    public MainService() {

    }

    public void createAccount(Scanner in) throws ParseException{

        ///UserApp newUserApp = UserAppFactory.createUserApp(in);
        CinemaGoer newCinemaGoer = CinemaGoerFactory.createCinemaGoer(in);
        System.out.println(newCinemaGoer);
        //UserApp newUserApp = new UserApp(newCinemaGoer.getId(), newCinemaGoer.getName(),
         //       newCinemaGoer.getAddress(), newCinemaGoer.getEmail());

       //this.users.add(newUserApp);
       this.cinemaGoers.add(newCinemaGoer);
       if(this.cinemaGoerDatabase != null)
           this.cinemaGoerDatabase.create(newCinemaGoer);
      // if(this.userAppDatabase!=null)
           //this.userAppDatabase.create(newUserApp);
       System.out.println("Account created");
       System.out.println(newCinemaGoer);
    }

    public void seeUsers(){
        if(this.userAppDatabase!=null)
            this.userAppDatabase.read();
    }
    public void seeAwards(){
        System.out.println(this.awardDatabase);
        List<Award> awards = new ArrayList<>();
        if(this.awardDatabase !=null)
            awards = this.awardDatabase.read();
        for(int i = 0; i < awards.size(); i++)
            System.out.println(awards.get(i));

    }
    public void seeReviews(){
        if(this.reviewDatabase!=null)
            this.reviewDatabase.read();
    }

    public void UpdateAccount(Scanner in) throws ParseException{
      CinemaGoer newCinemaGoer = new CinemaGoer(in);
      UserApp newUserApp = new UserApp(newCinemaGoer.getId(), newCinemaGoer.getName(),
                newCinemaGoer.getAddress(), newCinemaGoer.getEmail());
      if(this.cinemaGoerDatabase != null)
          this.cinemaGoerDatabase.update(newCinemaGoer);
      if(this.userAppDatabase != null)
          this.userAppDatabase.update(newUserApp);

      System.out.println("Account updated");

    }

    public void DeleteAccount(Scanner in) throws ParseException{
        CinemaGoer newCinemaGoer = new CinemaGoer(in);
        UserApp newUserApp = new UserApp(newCinemaGoer.getId(), newCinemaGoer.getName(),
                newCinemaGoer.getAddress(), newCinemaGoer.getEmail());
        if(this.cinemaGoerDatabase != null)
            this.cinemaGoerDatabase.delete(newCinemaGoer);
        if(this.userAppDatabase != null)
            this.userAppDatabase.delete(newUserApp);

        System.out.println("Account deleted");
    }

    public void AddReview(Scanner in) throws ParseException{
        Review review = ReviewFactory.createReview(in);
        if(this.reviewDatabase != null)
            this.reviewDatabase.create(review);
    }

    public void DeleteReview(Scanner in) throws ParseException{
        Review review = new Review(in);
        if(this.reviewDatabase != null)
            reviewDatabase.delete(review);
    }
}
