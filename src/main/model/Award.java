package main.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Award {
    private int id;
    private String result; /// nominee, winner
    private String nameAward;

    public Award(int awardId, Scanner in) throws ParseException {
        this.id = awardId;
        this.read(in);
    }

    public Award(int awardId, ResultSet in) throws SQLException {
        this.id = awardId;
        this.read(in);
    }

    public void read(ResultSet in) throws SQLException{
        this.result = in.getString("result");
        this.nameAward = in.getString("nameAward");
    }

    public void read(Scanner in) throws  ParseException{
        System.out.println("Result: ");
        this.result = in.nextLine();
        System.out.println("Name of the award: ");
        this.nameAward = in.nextLine();

    }
    public Award(int idAward, String result, String nameAward){
        this.id=idAward;
        this.result = result;
        this.nameAward = nameAward;
    }

    public String getResult() {return result;}
    public String getNameAward() {return nameAward;}

    public int getId() {return id;}
    public String toCSV() {
        int var = this.id;
        return "" + var + "," + this.result + "," + this.nameAward ;
    }

}
