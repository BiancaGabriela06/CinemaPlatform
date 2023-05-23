package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import main.model.*;
import main.repository.ActorRepository.ActorDatabase;
import main.repository.AwardRepository.AwardDatabase;
import main.repository.CinemaGoerRepository.CinemaGoerDatabase;
import main.repository.ReviewRepository.ReviewDatabase;
import main.repository.UserAppRepository.UserAppDatabase;
import main.service.AppService;
import main.service.CinemaGoerService;
import main.service.ProductionCompanyService;

public class Main {


    static List<String> availableCommandsStart = Arrays.asList(
            "create_account", "see_users", "see_awards", "see_reviews");
    static List<String> availableCommandsAfter = Arrays.asList(
            "update_account", "delete_account", "add_review", "delete_review");
    static List<String> commandsDescriptionStart = Arrays.asList(
            "Creeaza cont pentru a vedea/a da review la filme/seriale",
            "Vezi utilizatorii site-ului",
            "Vezi premiile luate de actori",
            "Vezi review-urile date de utilizatorii site-ului");

    static List<String> commandsDescriptionAfter = Arrays.asList(
            "Modifica profilul", "Sterge-ti profilul", "Adauga un review", "Sterge un review");

    private static void printCommands1(){
        for(int i = 0; i < availableCommandsStart.size(); ++i)
           System.out.println((i+1) + ": " + availableCommandsStart.get(i) + " (" + commandsDescriptionStart.get(i) + ")");
    }

    private static void printCommands2(){
        for(int i = 0; i < availableCommandsAfter.size(); ++i)
            System.out.println((i+1) + ": " + availableCommandsAfter.get(i) + " (" + commandsDescriptionAfter.get(i) + ")");
    }

    public static Connection getConnection(){
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/javadb";
            String username = "root";
            String password = "mysql123";

            System.out.println("Connected");
            var conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            return  conn;
            ///conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        var connection = Main.getConnection();
        System.out.println(connection);

        var actorDatabase = new ActorDatabase(connection);
        var awardDatabase = new AwardDatabase(connection);
        var cinemaGoerDatabase = new CinemaGoerDatabase(connection);
        var reviewDatabase =  new ReviewDatabase(connection);
        var userAppDatabase = new UserAppDatabase(connection);

        MainService mainService = new MainService(actorDatabase, cinemaGoerDatabase, awardDatabase,
                reviewDatabase, userAppDatabase);
        ///AuditService auditService = new AuditService();

        App app = new App();
        AppService appService = new AppService();
        boolean end = false;

        while(!end){
            System.out.println("Insert command: (help - see commands)");
            String command = in.nextLine().toLowerCase(Locale.ROOT);

            try{
                switch(command){
                    case "create_account" -> mainService.createAccount(in);
                    case "see_users" -> mainService.seeUsers();
                    case "see_awards" -> mainService.seeAwards();
                    case "see_reviews" -> mainService.seeReviews();
                    case "help" -> Main.printCommands1();
                    case "end" -> end = true;
                }
                if(availableCommandsStart.contains(command)){
                    //auditService.logAction(command);
                    if(command == "create_account"){
                        System.out.println("Welcome to our platform.");
                        boolean end2 = false;
                        while(!end2)
                        {
                            System.out.println("Insert command help to see commands");
                            String command1 = in.nextLine().toLowerCase(Locale.ROOT);
                            try{

                                switch (command1){
                                    case "update_account" -> mainService.UpdateAccount(in);
                                    case "delete_account" -> mainService.DeleteAccount(in);
                                    case "add_review" -> mainService.AddReview(in);
                                    case "delete_review" -> mainService.DeleteReview(in);
                                    case "help" -> Main.printCommands2();
                                    case "end" -> end2 = true;
                                }
                                //if(availableCommandsAfter.contains(command1))
                                    //auditService.logAction(command1);
                            }catch (Exception e){
                                System.out.println(e.toString());
                            }
                        }

                    }
                }
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }

        //appService.initializeApp(app);


    }

}
