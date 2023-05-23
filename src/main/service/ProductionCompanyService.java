package main.service;

import java.util.Iterator;
import java.util.List;
import main.model.*;

public class ProductionCompanyService {
    public ProductionCompanyService() {
    }

    public void addMovie(ProductionCompany productionCompany, Movie movie, App app) {
        List<Show> showsCompany = productionCompany.getShows();
        showsCompany.add(movie);
        List<Show> showsApp = app.getShows();
        showsApp.add(movie);
        productionCompany.setShows(showsCompany);
        app.setShows(showsApp);
    }

    public void addSeries(ProductionCompany productionCompany, Series series, App app) {
        List<Show> showsCompany = productionCompany.getShows();
        showsCompany.add(series);
        List<Show> showsApp = app.getShows();
        showsApp.add(series);
        productionCompany.setShows(showsCompany);
        app.setShows(showsApp);
    }

    public void addAwardtoActor(App app, int id, Award award) {
        boolean ok = false;
        Iterator var5 = app.getActors().iterator();

        while(var5.hasNext()) {
            Actor a = (Actor)var5.next();
            if (a.getId() == (long)id) {
                a.addAward(award);
                ok = true;
            }
        }

        if (!ok) {
            System.out.println("The Actor with id " + id + " doesn't exist");
        } else if (ok) {
            System.out.println("Award added with succes");
        }

    }
}
