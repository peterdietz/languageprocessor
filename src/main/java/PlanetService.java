/**
 * Created by peterdietz on 7/25/17.
 */
public interface PlanetService {
    boolean sendMessage(String message);
    String getNameOfPlanet();
    Integer getPlanetNumber();
    Boolean canYouLiveThere();
}
