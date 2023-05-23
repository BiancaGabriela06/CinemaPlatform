package main.repository.ReviewRepository;

import main.model.Award;
import main.model.Review;
import main.repository.AwardRepository.AwardFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class ReviewFactory {
    private static int uniqueid = 0;

    public static void incrementUniqueId(int inc){
        ReviewFactory.uniqueid += inc;
    }

    public static Review createReview(Scanner in) throws ParseException {
        return new Review(uniqueid++, in);
    }

    public static Review createReview(ResultSet in) throws SQLException {
        return new Review(uniqueid++,  in);
    }
}
