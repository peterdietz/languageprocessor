/**
 * Created by peterdietz on 7/25/17.
 */
public class PlanetFeature {
    private PlanetService planetService;

    public PlanetFeature() {
        this(RestApiInjector.getInstance(PlanetService.class));
    }

    PlanetFeature(PlanetService planetService) {
        this.planetService = planetService;
    }

    public boolean sendMessage(String message){
        return planetService.sendMessage(message);
    }

    public static void main(String[] args){
        try {
            PlanetFeature planetFeature = new PlanetFeature();

            for(int i=0; i<Integer.MAX_VALUE;i++) {
                planetFeature.sendMessage("iteration["+i+"] sendMessage. canYouLiveThere?: " + planetFeature.planetService.canYouLiveThere());
            }

        } catch (Exception e){
            System.err.println("main:" + e);
        }
    }
}
