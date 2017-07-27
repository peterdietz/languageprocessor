import com.google.inject.Singleton;

/**
 * Created by peterdietz on 7/25/17.
 */
@Singleton
public class MarsPlanetService implements PlanetService {
    @Override
    public boolean sendMessage(String message) {
        System.out.println("Hello Mars: " + message);
        return true;
    }
}
