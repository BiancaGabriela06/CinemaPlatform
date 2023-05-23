package main.repository.ReviewRepository;

import main.model.Award;
import main.model.Review;
import main.repository.AwardRepository.AwardFactory;
import main.repository.AwardRepository.AwardSingleton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReviewSingleton {
    private static ReviewSingleton single_instance = null;
    private final ReviewFactory reviewFactory = new ReviewFactory();
    private List<Review> reviews = new ArrayList<>();

    public ReviewSingleton(){
    }

    public static ReviewSingleton getInstance(){
        if(single_instance == null)
            single_instance = new ReviewSingleton();
        return single_instance;
    }

    public void setReviews(List<Review> reviews) {this.reviews = reviews;}
    public List<Review> getReviews(){ return this.reviews;}

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
            System.out.println("No saved reviews");
        }
        return columns;
    }

    public void loadFromCSV(){
        try{
            List<String[]> columns = getCSVColumns("data/review.csv");
            Iterator var2 = columns.iterator();
            while(var2.hasNext()){
                String[] fields = (String[])var2.next();
                //// idreview, stars, comment, idcinemagoer, idshow
                Review newReview = new Review(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]),
                        fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4]));
                this.reviews.add(newReview);
            }
            ReviewFactory.incrementUniqueId(columns.size());
        } catch (NumberFormatException var5) {
            System.out.println(var5.toString());
        }
    }

    public void dumpToCSV() {
        try {
            FileWriter writer = new FileWriter("data/review.csv");
            Iterator var2 = this.reviews.iterator();

            while(var2.hasNext()) {
                Review review = (Review)var2.next();
                writer.write(review.toCSV());
                writer.write("\n");
            }

            writer.close();
        } catch (IOException var4) {
            System.out.println(var4.toString());
        }

    }
}
