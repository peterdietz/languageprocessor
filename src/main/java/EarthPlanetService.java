import com.google.inject.Singleton;

/**
 * Created by peterdietz on 7/25/17.
 */
@Singleton
public class EarthPlanetService implements PlanetService {


    @Override
    public boolean sendMessage(String message) {
        System.out.println(getNameOfPlanet()+"["+getPlanetNumber()+"]: " + message);
        return true;
    }

    @Override
    public String getNameOfPlanet() {
        return "Earth";
    }

    @Override
    public Integer getPlanetNumber() {
        return 3;
    }
}
