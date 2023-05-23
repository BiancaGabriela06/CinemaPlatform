package main.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Actor {
    private int id;
    private  String name;
    private  int age;
    private List<Award> awards;

    public Actor(int actorId, Scanner in) throws ParseException{
        this.id = actorId;
        this.read(in);
    }

    public Actor(int actorId, ResultSet in) throws  SQLException{
        this.id = actorId;
        this.read(in);
    }

    public void read(ResultSet in) throws SQLException{
        this.name = in.getString("name");
        this.age = in.getInt("age");
    }
    public void read(Scanner in) throws  ParseException{
        System.out.println("Name: ");
        this.name = in.nextLine();
        System.out.println("Age: ");
        this.age = in.nextInt();

    }
    public Actor(int id, String name, int age){
        this.id = id;
        this.age = age;
        this.name = name;
        awards = new ArrayList<>();
    }

    public String getName() {return name;}
    public int getAge() {return age;}
    public int getId(){
        return id;
    }

    public List<Award> getAwards(){
        return awards;
    }

    public void setAwards(List<Award> awards){
        this.awards = awards;
    }
    public void addAward(Award award){
           awards.add(award);
    }

    public String toCSV() {
        int var = this.id;
        return "" + var + "," + this.name + "," + this.age ;
    }

    public String toString(){
        return "Actor's name: " + name + ", age " + age;
    }

}
