import org.ff4j.FF4j;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by peterdietz on 8/1/17.
 */
public class GenericPlanetService implements PlanetService {
    PlanetService planetService;
    private static FF4j ff4j;

    //concretely defined impls
    EarthPlanetService earthPlanetService = new EarthPlanetService();
    MarsPlanetService marsPlanetService = new MarsPlanetService();

    public GenericPlanetService(){
        init();
    }

    public void init() {
        try {
            URL restURL = new URL("http://localhost:8080");
            ff4j = new FF4j(restURL);
        } catch (MalformedURLException e) {
            System.err.println("FeatureInjector:" + e.getMessage());
        }

        updateService();
    }

    private void updateService(){
        try {
            if (ff4j.exist("mars") && ff4j.check("mars")) {
                setService(marsPlanetService);
            } else {
                setService(earthPlanetService);
            }
        } catch (Exception e) {
            System.err.println("Update: " + e.toString());
        }
    }

    public PlanetService getService() {
        updateService();
        return this.planetService;
    }

    public void setService(PlanetService service){
        this.planetService = service;
    }

    @Override
    public boolean sendMessage(String message) {
        return getService().sendMessage(message);
    }

    @Override
    public String getNameOfPlanet() {
        return getService().getNameOfPlanet();
    }

    @Override
    public Integer getPlanetNumber() {
        return getService().getPlanetNumber();
    }
}
