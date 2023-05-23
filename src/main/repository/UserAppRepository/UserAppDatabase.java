package main.repository.UserAppRepository;

import main.model.Award;
import main.model.UserApp;
import main.repository.AwardRepository.AwardFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserAppDatabase {
    Connection connection;
    UserAppFactory userAppFactory = new UserAppFactory();

    public UserAppDatabase(Connection connection) {this.connection = connection;}

    public List<UserApp> read(){
        List<UserApp> users = new ArrayList();

        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM USERAPP");

            while(result.next()) {
                UserApp current = this.userAppFactory.createUserApp(result);
                users.add(current);
            }

            statement.close();
        } catch (Exception var5) {
            System.out.println(var5.toString());
        }

        return users;
    }

    public void update(UserApp newUser){
        try{
            String query = "UPDATE UserApp SET name = ?, address = ?, email = ? Where iduser = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, newUser.getName());
            preparedStatement.setString(2, newUser.getAddress());
            preparedStatement.setString(3, newUser.getEmail());
            preparedStatement.setInt(4, newUser.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception var4){
            System.out.println(var4.toString());
        }
    }

    public void create(UserApp user){
        try{
            String query = "Insert into UserApp(iduser, name, address, email) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.execute();
            preparedStatement.close();
        }catch(Exception var4) {
            System.out.println(var4.toString());
        }
    }

    public void delete(UserApp user){
        try{
            String query = "DELETE FROM UserApp where iduser = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.execute();
            preparedStatement.close();
        }catch(Exception var4){
            System.out.println(var4.toString());
        }
    }
}
