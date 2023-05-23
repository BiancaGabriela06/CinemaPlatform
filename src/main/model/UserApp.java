package main.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class UserApp {
    private int id;
    private String name;
    private String address;
    private String email;
    public UserApp(int id){
        this.id = id;
    }

    public UserApp(String name, String address, String email){
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public UserApp(int id, String name, String address, String email){
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }
    public UserApp(Scanner in) throws ParseException{
        this.read2(in);
    }
    public void read2(Scanner in) throws ParseException{
        System.out.println("Account'id: ");
        this.id = in.nextInt();
        System.out.println("Name: ");
        this.name = in.nextLine();
        System.out.println("Address: ");
        this.address = in.nextLine();
        System.out.println("Email: ");
        this.email = in.nextLine();

    }

    public UserApp(int userappId, Scanner in) throws ParseException {
        this.id = userappId;
        this.read(in);
    }

    public UserApp(int userappId, ResultSet in) throws SQLException {
        this.id = userappId;
        this.read(in);
    }

    public void read(ResultSet in) throws SQLException{
        this.name = in.getString("name");
        this.address = in.getString("address");
        this.email = in.getString("email");
    }
    public void read(Scanner in) throws  ParseException{
        System.out.println("Name: ");
        this.name = in.nextLine();
        System.out.println("Address: ");
        this.address = in.nextLine();
        System.out.println("Email: ");
        this.email = in.nextLine();
    }

    public String toCSV() {

        return "" + this.id + "," + this.name + "," + this.address + "," + this.email;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getAddress(){
        return address;
    }


    public String getEmail(){
        return email;
    }

    public void setEmail(){
        this.email = email;
    }

    public String toString(){
        return "User's id: "+ id + ", name: " + name + ", address: " + address + ", email: " + email ;
    }
}
