import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.ff4j.FF4j;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by peterdietz on 7/25/17.
 */
public class FeatureInjector extends AbstractModule {
    private PlanetService service;
    private FF4j ff4j;

    public FeatureInjector() {
        refreshFeatureFlip();
    }

    private void refreshFeatureFlip(){
        try {
            InputStream exportXML = new URL("http://localhost:8080/ff4j-web-console/api/export").openStream();
            ff4j = new FF4j(exportXML);
            if (ff4j.exist("mars") && ff4j.check("mars")) {
                setService(new MarsPlanetService());
            } else {
                setService(new EarthPlanetService());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public PlanetService getService() {
        refreshFeatureFlip();
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
            featureInjector.refreshFeatureFlip();

            if (featureInjector.ff4j.exist("cleveland_rocks") && featureInjector.ff4j.check("cleveland_rocks")) {
                System.out.println("YES, cleveland_rocks enabled");
            } else {
                System.out.println("NO, cleveland_rocks not enabled");
            }

            for(int i=0; i<Integer.MAX_VALUE;i++) {
                featureInjector.sendMessage("iteration["+i+"] getService().sendMessage(msg)");
                //TimeUnit.SECONDS.sleep(2);
            }

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
