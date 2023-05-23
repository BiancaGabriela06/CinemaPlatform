package main.repository.AwardRepository;

import main.model.Actor;
import main.model.Award;
import main.repository.ActorRepository.ActorFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class AwardFactory {
    private static int uniqueid = 0;
    public static void incrementUniqueId(int inc){
        AwardFactory.uniqueid += inc;
    }

    public Award createAward(Scanner in) throws ParseException {
        return new Award(uniqueid++, in);
    }

    public Award createAward(ResultSet in) throws SQLException {
        return new Award(uniqueid++, in);
    }
}
