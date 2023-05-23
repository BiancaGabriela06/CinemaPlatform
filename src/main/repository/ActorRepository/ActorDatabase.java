package main.repository.ActorRepository;

import main.model.Actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ActorDatabase {

    Connection connection;
    ActorFactory actorFactory = new ActorFactory();

    public ActorDatabase(Connection connection) {this.connection = connection;}

    public List<Actor> read(){
        List<Actor> actors = new ArrayList();

        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM ACTOR");

            while(result.next()) {
                Actor current = this.actorFactory.createActor(result);
                actors.add(current);
            }

            statement.close();
        } catch (Exception var5) {
            System.out.println(var5.toString());
        }

        return actors;
    }

    public void update(Actor newActor){
        try{
            String query = "UPDATE Actor SET name = ?, age = ? Where idactor = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, newActor.getName());
            preparedStatement.setInt(2, newActor.getAge());
            preparedStatement.setInt(3, newActor.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception var4){
            System.out.println(var4.toString());
        }
    }

    public void create(Actor actor){
        try{
            String query = "Insert into Actor(idactor, name, age) VALUES (?,?,?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, actor.getId());
            preparedStatement.setString(2, actor.getName());
            preparedStatement.setInt(3, actor.getAge());
            preparedStatement.execute();
            preparedStatement.close();
        }catch(Exception var4) {
            System.out.println(var4.toString());
        }
    }

    public void delete(Actor actor){
        try{
            String query = "DELETE FROM Actor where idactor =?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, actor.getId());
            preparedStatement.execute();
            preparedStatement.close();
        }catch(Exception var4){
            System.out.println(var4.toString());
        }
    }
}
