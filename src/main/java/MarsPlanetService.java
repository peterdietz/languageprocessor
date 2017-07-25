import com.google.inject.Singleton;

/**
 * Created by peterdietz on 7/25/17.
 */
@Singleton
public class MarsPlanetService implements PlanetService {
    @Override
    public boolean sendPlanet(String message, String recipient) {
        System.out.println("Hello Mars");
        return true;
    }
}
