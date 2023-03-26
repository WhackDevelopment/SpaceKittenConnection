package de.safespacegerman.spacekitten.server.routing;

import de.safespacegerman.spacekitten.types.RequestData;
import de.safespacegerman.spacekitten.types.payloads.DataPayload;
import de.safespacegerman.spacekitten.types.payloads.Payload;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.server.routing:RouteExecutor
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public abstract class RouteExecutor extends HttpHandler {

    private String path;

    public RouteExecutor(String path) {
        this.path = path;
    }

    protected abstract Payload execute(RequestData requestData);

    @Override
    public void service(Request request, Response response) {
        try {
            Payload payload = execute(RequestData.from(request));
            payload.write(response);
        } catch (Exception exception) {
            exception.printStackTrace();
            try {
                DataPayload.internalServerError(exception).write(response);
            } catch (Exception e) {
            }
        }
    }

    public String path() {
        return path;
    }

}
