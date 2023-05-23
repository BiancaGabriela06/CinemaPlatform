package main.repository.UserAppRepository;

import main.model.Award;
import main.model.UserApp;
import main.repository.AwardRepository.AwardFactory;
import main.repository.AwardRepository.AwardSingleton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UserAppSingleton {
    private static UserAppSingleton single_instance = null;
    private final UserAppFactory userAppFactory = new UserAppFactory();
    private List<UserApp> users = new ArrayList<>();

    public UserAppSingleton(){

    }

    public static UserAppSingleton getInstance(){
        if(single_instance == null)
            single_instance = new UserAppSingleton();
        return single_instance;
    }

    public void setUsers(List<UserApp> users) {this.users = users;}
    public List<UserApp> getUsers(){ return this.users;}

    private static List<String[]> getCSVColumns(String filename){
        List<String[]> columns = new ArrayList<>();
        try{
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String line;
            try{
                while((line = in.readLine()) != null)
                {
                    String[] fields = line.replaceAll(" ", "").split(",");
                    columns.add(fields);
                }
            }catch(Throwable var6){
                try{
                    in.close();
                }catch(Throwable var5){
                    var6.addSuppressed(var5);
                }
                throw  var6;
            }
            in.close();
        }catch(IOException var7){
            System.out.println("No saved users");
        }
        return columns;
    }

    public void loadFromCSV(){
        try{
            List<String[]> columns = getCSVColumns("data/users.csv");
            Iterator var2 = columns.iterator();
            while(var2.hasNext()){
                String[] fields = (String[])var2.next();
                UserApp user = new UserApp(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3]);
                this.users.add(user);
            }
            UserAppFactory.incrementUniqueId(columns.size());
        } catch (NumberFormatException var5) {
            System.out.println(var5.toString());
        }
    }

    public void dumpToCSV() {
        try {
            FileWriter writer = new FileWriter("data/users.csv");
            Iterator var2 = this.users.iterator();

            while(var2.hasNext()) {
                UserApp user = (UserApp) var2.next();
                writer.write(user.toCSV());
                writer.write("\n");
            }

            writer.close();
        } catch (IOException var4) {
            System.out.println(var4.toString());
        }

    }
}
