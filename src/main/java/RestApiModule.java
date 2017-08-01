import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * Created by brettcooper on 6/12/17.
 */
public class RestApiModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PlanetService.class).to(GenericPlanetService.class).in(Scopes.SINGLETON);
        //bind(CampaignRepository.class).to(DynamoDBCampaignRepository.class).in(Scopes.SINGLETON);
    }
}
