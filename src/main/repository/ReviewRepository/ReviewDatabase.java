package main.repository.ReviewRepository;

import main.model.Award;
import main.model.Review;
import main.repository.AwardRepository.AwardFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReviewDatabase {
    Connection connection;
    ReviewFactory reviewFactory = new ReviewFactory();

    public ReviewDatabase(Connection connection) {this.connection = connection;}

    public List<Review> read(){
        List<Review> reviews = new ArrayList();

        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM REVIEW");

            while(result.next()) {
                Review current = this.reviewFactory.createReview(result);
                reviews.add(current);
            }

            statement.close();
        } catch (Exception var5) {
            System.out.println(var5.toString());
        }

        return reviews;
    }

    public void update(Review newReview){
        try{
            String query = "UPDATE REVIEW SET stars = ?, comment = ? Where idaward = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, newReview.getStars());
            preparedStatement.setString(2, newReview.getComment());
            preparedStatement.setInt(3, newReview.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception var4){
            System.out.println(var4.toString());
        }
    }

    public void create(Review review){
        try{
            String query = "Insert into Review(idreviw, stars, comment, idcinemagoer, idshow) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, review.getId());
            preparedStatement.setInt(2, review.getStars());
            preparedStatement.setString(3, review.getComment());
            preparedStatement.setInt(4, review.getCinemaGoerId());
            preparedStatement.setInt(5, review.getShowId());
            preparedStatement.execute();
            preparedStatement.close();
        }catch(Exception var4) {
            System.out.println(var4.toString());
        }
    }

    public void delete(Review review){
        try{
            String query = "DELETE FROM REVIEW where idreview = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, review.getId());
            preparedStatement.execute();
            preparedStatement.close();
        }catch(Exception var4){
            System.out.println(var4.toString());
        }
    }
}
