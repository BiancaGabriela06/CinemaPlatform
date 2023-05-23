package main.repository.ActorRepository;

import main.model.Actor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActorSingleton {
    private static ActorSingleton single_instance = null;
    private final ActorFactory actorFactory = new ActorFactory();
    private List<Actor> actors = new ArrayList<>();

    public ActorSingleton(){

    }

    public static ActorSingleton getInstance(){
        if(single_instance == null)
            single_instance = new ActorSingleton();
        return single_instance;
    }

    public void setActors(List<Actor> actors) {this.actors = actors;}
    public List<Actor> getActors(){ return this.actors;}

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
            System.out.println("No saved actors");
        }
        return columns;
    }

    public void loadFromCSV(){
        try{
            List<String[]> columns = getCSVColumns("data/actors.csv");
            Iterator var2 = columns.iterator();
            while(var2.hasNext()){
                String[] fields = (String[])var2.next();
                Actor newActor = new Actor(Integer.parseInt(fields[0]), fields[1], Integer.parseInt(fields[2]));
                this.actors.add(newActor);
            }
            ActorFactory.incrementUniqueId(columns.size());
        } catch (NumberFormatException var5) {
            System.out.println(var5.toString());
        }
    }

    public void dumpToCSV() {
        try {
            FileWriter writer = new FileWriter("data/actor.csv");
            Iterator var2 = this.actors.iterator();

            while(var2.hasNext()) {
                Actor actor = (Actor)var2.next();
                writer.write(actor.toCSV());
                writer.write("\n");
            }

            writer.close();
        } catch (IOException var4) {
            System.out.println(var4.toString());
        }

    }
}
