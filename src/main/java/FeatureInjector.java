import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.ff4j.FF4j;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by peterdietz on 7/25/17.
 */
public class FeatureInjector extends AbstractModule {
    private PlanetService service;

    @Inject
    public void setService(PlanetService planetService) {
        this.service = planetService;
    }

    public boolean sendMessage(String message, String to){
        return service.sendPlanet(message, to);
    }

    @Override
    protected void configure() {
        bind(PlanetService.class).to(EarthPlanetService.class);
    }

    public static void main(String[] args){
        try {
            InputStream exportXML = new URL("http://localhost:8080/ff4j-web-console/api/export").openStream();
            FF4j ff4j = new FF4j(exportXML);
            FeatureInjector featureInjector = new FeatureInjector();
            EarthPlanetService earthPlanetService = new EarthPlanetService();
            featureInjector.setService(earthPlanetService);

            if (ff4j.check("cleveland_rocks")) {
                System.out.println("YES, CLE ROCKS");
            } else {
                System.out.println("NO, CLE DOES NOT ROCK");
            }


            if(ff4j.check("mars")) {
                MarsPlanetService marsPlanetService = new MarsPlanetService();
                featureInjector.setService(marsPlanetService);
            }



            featureInjector.sendMessage("abc", "xyz");

        } catch (Exception e){
            System.err.println(e);
        }


        /*
            ApiConfig apiConfig = new ApiConfig();
            apiConfig.setPort(8080);
            apiConfig.setHost("localhost");
            apiConfig.setWebContext("/v2/ff");
        */
    }


}
