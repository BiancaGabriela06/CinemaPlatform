package main.repository.CinemaGoerRepository;

import main.model.CinemaGoer;
import main.model.UserApp;
import main.repository.UserAppRepository.UserAppDatabase;
import main.repository.UserAppRepository.UserAppFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CinemaGoerDatabase {
    Connection connection;
    CinemaGoerFactory cinemaGoerFactory = new CinemaGoerFactory();

    public CinemaGoerDatabase(Connection connection) {this.connection = connection;}
    UserAppDatabase userAppDatabase = new UserAppDatabase(connection);

    public List<CinemaGoer> read(){
        List<CinemaGoer> cinemaGoers= new ArrayList();

        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM CinemaGoer");

            while(result.next()) {
                CinemaGoer current = this.cinemaGoerFactory.createCinemaGoer(result);
                cinemaGoers.add(current);
            }

            statement.close();
        } catch (Exception var5) {
            System.out.println(var5.toString());
        }

        return cinemaGoers;
    }

    public void update(CinemaGoer newCinemaGoer){
        userAppDatabase.update(newCinemaGoer);

        try{
            String query = "UPDATE CinemaGoer SET phonenumber = ? Where idcinemagoer = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, newCinemaGoer.getPhoneNumber());
            preparedStatement.setInt(2, newCinemaGoer.getId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        }catch (Exception var4){
            System.out.println(var4.toString());
        }
    }

    public void create(CinemaGoer cinemaGoer){
        userAppDatabase.create(cinemaGoer);
        try{
            String query = "Insert into CinemaGoer(idcinemagoer, phonenumber) VALUES (?,?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, cinemaGoer.getId());
            preparedStatement.setString(2, cinemaGoer.getPhoneNumber());
            preparedStatement.execute();
            preparedStatement.close();
        }catch(Exception var4) {
            System.out.println(var4.toString());
        }
    }

    public void delete(CinemaGoer cinemaGoer){
        userAppDatabase.delete(cinemaGoer);
        try{
            String query = "DELETE FROM CinemaGoer where idcinemagoer = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, cinemaGoer.getId());
            preparedStatement.execute();
            preparedStatement.close();
        }catch(Exception var4){
            System.out.println(var4.toString());
        }
    }
}
