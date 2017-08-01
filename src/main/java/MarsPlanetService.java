import com.google.inject.Singleton;

/**
 * Created by peterdietz on 7/25/17.
 */
@Singleton
public class MarsPlanetService implements PlanetService {
    @Override
    public boolean sendMessage(String message) {
        System.out.println(getPlanetNumber() + "-" + getNameOfPlanet() + ": " + message);
        return true;
    }

    @Override
    public String getNameOfPlanet() {
        return "Mars";
    }

    @Override
    public Integer getPlanetNumber() {
        return 4;
    }

    @Override
    public Boolean canYouLiveThere() {
        return false;
    }
}
