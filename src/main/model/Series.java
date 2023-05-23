package main.model;

import java.util.List;

public class Series extends Show {
    private int nrSeasons;
    private int nrEpisodes;

    public Series(long id, String name, ProductionCompany productionCompany, List<Actor> actors, int nrSeasons, int nrEpisodes){
        super(id, name, productionCompany, actors);
        this.nrSeasons = nrSeasons;
        this.nrEpisodes = nrEpisodes;
    }

    @Override
    public String toString(){
        return super.toString() + ", Number of Seasons:  " + nrSeasons + " and Number of Episodes: " + nrEpisodes;
    }
}
