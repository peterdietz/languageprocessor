import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * Created by peterdietz on 7/7/17.
 */
public class Hello implements RequestHandler<Integer, String> {


    @Override
    public String handleRequest(Integer input, Context context) {
        return context.getFunctionName() + String.valueOf(input) + " -- " + context.getInvokedFunctionArn();
    }
}
