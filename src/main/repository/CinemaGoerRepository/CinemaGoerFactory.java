package main.repository.CinemaGoerRepository;

import main.model.CinemaGoer;
import main.model.UserApp;
import main.repository.UserAppRepository.UserAppFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class CinemaGoerFactory {
    private static int uniqueid = 0;

    public static void incrementUniqueId(int inc){
        CinemaGoerFactory.uniqueid += inc;
    }

    public static CinemaGoer createCinemaGoer(Scanner in) throws ParseException {
        return new CinemaGoer(uniqueid++, in);
    }

    public static CinemaGoer createCinemaGoer(ResultSet in) throws SQLException {
        return new CinemaGoer(uniqueid++, in);
    }
}
