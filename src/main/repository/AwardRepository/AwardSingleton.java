package main.repository.AwardRepository;

import main.model.Actor;
import main.model.Award;
import main.repository.ActorRepository.ActorFactory;
import main.repository.ActorRepository.ActorSingleton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AwardSingleton {
    private static AwardSingleton single_instance = null;
    private final AwardFactory awardFactory = new AwardFactory();
    private List<Award> awards = new ArrayList<>();

    public AwardSingleton(){

    }

    public static AwardSingleton getInstance(){
        if(single_instance == null)
            single_instance = new AwardSingleton();
        return single_instance;
    }

    public void setAwards(List<Award> awards) {this.awards = awards;}
    public List<Award> getAwards(){ return this.awards;}

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
            System.out.println("No saved awards");
        }
        return columns;
    }

    public void loadFromCSV(){
        try{
            List<String[]> columns = getCSVColumns("data/award.csv");
            Iterator var2 = columns.iterator();
            while(var2.hasNext()){
                String[] fields = (String[])var2.next();
                Award newAward = new Award(Integer.parseInt(fields[0]), fields[1], fields[2]);
                this.awards.add(newAward);
            }
            AwardFactory.incrementUniqueId(columns.size());
        } catch (NumberFormatException var5) {
            System.out.println(var5.toString());
        }
    }

    public void dumpToCSV() {
        try {
            FileWriter writer = new FileWriter("data/award.csv");
            Iterator var2 = this.awards.iterator();

            while(var2.hasNext()) {
                Award award = (Award)var2.next();
                writer.write(award.toCSV());
                writer.write("\n");
            }

            writer.close();
        } catch (IOException var4) {
            System.out.println(var4.toString());
        }

    }
}
