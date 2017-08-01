import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by peterdietz on 8/1/17.
 */
public class RestApiInjector {

    private static final Injector injector;

    static {
        injector = Guice.createInjector(
                new RestApiModule());
    }


    public static <T> T getInstance(Class<T> type) {
        return injector.getInstance(type);
    }
}
