package main.repository.ActorRepository;

import main.model.Actor;
import main.model.Award;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class ActorFactory {
    private static int uniqueid = 0;
    public static void incrementUniqueId(int inc){
        ActorFactory.uniqueid += inc;
    }

    public Actor createActor(Scanner in) throws ParseException{
        return new Actor(uniqueid++, in);
    }

    public Actor createActor(ResultSet in) throws SQLException{
        return new Actor(uniqueid++, in);
    }
}
