import com.google.inject.AbstractModule;
import org.ff4j.FF4j;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by peterdietz on 7/25/17.
 */
public class FeatureInjector extends AbstractModule {
    private PlanetService service;
    private static FF4j ff4j;

    public FeatureInjector() {
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
                setService(new MarsPlanetService());
            } else {
                setService(new EarthPlanetService());
            }
        } catch (Exception e) {
            System.err.println("Update: " + e.toString());
        }
    }

    public PlanetService getService() {
        updateService();
        return this.service;
    }

    public void setService(PlanetService service){
        this.service = service;
    }

    public boolean sendMessage(String message){
        return getService().sendMessage(message);
    }

    @Override
    protected void configure() {
        bind(PlanetService.class).to(EarthPlanetService.class);
    }

    public static void main(String[] args){
        try {
            FeatureInjector featureInjector = new FeatureInjector();
            featureInjector.updateService();

            if (featureInjector.ff4j.exist("cleveland_rocks") && featureInjector.ff4j.check("cleveland_rocks")) {
                System.out.println("YES, cleveland_rocks enabled");
            } else {
                System.out.println("NO, cleveland_rocks not enabled");
            }

            for(int i=0; i<Integer.MAX_VALUE;i++) {
                featureInjector.sendMessage("iteration["+i+"] getService().sendMessage(msg)");
            }

        } catch (Exception e){
            System.err.println("main:" + e);
        }
    }


}
