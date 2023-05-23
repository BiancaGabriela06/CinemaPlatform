package main.repository.AwardRepository;

import main.model.Actor;
import main.model.Award;
import main.repository.ActorRepository.ActorFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AwardDatabase {

    Connection connection;
    AwardFactory awardFactory = new AwardFactory();

    public AwardDatabase(Connection connection) {this.connection = connection;}

    public List<Award> read(){
        List<Award> awards = new ArrayList();

        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM AWARD");

            while(result.next()) {
                Award current = this.awardFactory.createAward(result);
                awards.add(current);
            }

            statement.close();
        } catch (Exception var5) {
            System.out.println(var5.toString());
        }

        return awards;
    }

    public void update(Award newAward){
        try{
            String query = "UPDATE Award SET result = ?, nameAward = ? Where idaward = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, newAward.getResult());
            preparedStatement.setString(2, newAward.getNameAward());
            preparedStatement.setInt(3, newAward.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception var4){
            System.out.println(var4.toString());
        }
    }

    public void create(Award award){
        try{
            String query = "Insert into Award(idaward, nameAward, result) VALUES (?,?,?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, award.getId());
            preparedStatement.setString(2, award.getNameAward());
            preparedStatement.setString(3, award.getResult());
            preparedStatement.execute();
            preparedStatement.close();
        }catch(Exception var4) {
            System.out.println(var4.toString());
        }
    }

    public void delete(Award award){
        try{
            String query = "DELETE FROM AWARD where idaward = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, award.getId());
            preparedStatement.execute();
            preparedStatement.close();
        }catch(Exception var4){
            System.out.println(var4.toString());
        }
    }
}
