package main.repository.CinemaGoerRepository;

import main.model.Award;
import main.model.CinemaGoer;
import main.repository.AwardRepository.AwardFactory;
import main.repository.AwardRepository.AwardSingleton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CinemaGoerSingleton {

    private static CinemaGoerSingleton single_instance = null;
    private final CinemaGoerFactory cinemaGoerFactory = new CinemaGoerFactory();
    private List<CinemaGoer> cinemaGoers = new ArrayList<>();

    public CinemaGoerSingleton(){

    }
    public static CinemaGoerSingleton getInstance(){
        if(single_instance == null)
            single_instance = new CinemaGoerSingleton();
        return single_instance;
    }

    public void setCinemaGoers(List<CinemaGoer> cinemaGoers) {this.cinemaGoers = cinemaGoers;}
    public List<CinemaGoer> getCinemaGoers(){ return this.cinemaGoers;}

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
            System.out.println("No saved cinemagoers");
        }
        return columns;
    }

    public void loadFromCSV(){
        try{
            List<String[]> columns = getCSVColumns("data/cinemagoer.csv");
            Iterator var2 = columns.iterator();
            while(var2.hasNext()){
                String[] fields = (String[])var2.next();
                CinemaGoer newCinemaGoer = new CinemaGoer(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3], fields[4]);
                this.cinemaGoers.add(newCinemaGoer);
            }
            CinemaGoerFactory.incrementUniqueId(columns.size());
        } catch (NumberFormatException var5) {
            System.out.println(var5.toString());
        }
    }

    public void dumpToCSV() {
        try {
            FileWriter writer = new FileWriter("data/cinemagoer.csv");
            Iterator var2 = this.cinemaGoers.iterator();

            while(var2.hasNext()) {
                CinemaGoer cinemaGoer = (CinemaGoer) var2.next();
                writer.write(cinemaGoer.toCSV());
                writer.write("\n");
            }

            writer.close();
        } catch (IOException var4) {
            System.out.println(var4.toString());
        }

    }
}
