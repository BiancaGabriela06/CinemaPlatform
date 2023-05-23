package main.repository.UserAppRepository;

import main.model.Award;
import main.model.UserApp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class UserAppFactory {
    private static int uniqueid = 0;
    public static void incrementUniqueId(int inc){
        UserAppFactory.uniqueid += inc;
    }

    public static UserApp createUserApp(Scanner in) throws ParseException {
        return new UserApp(uniqueid++, in);
    }

    public static UserApp createUserApp(ResultSet in) throws SQLException {
        return new UserApp(uniqueid++, in);
    }
}
