package de.safespacegerman.spacekitten.server.routes;

import com.google.gson.JsonObject;
import de.safespacegerman.spacekitten.SpaceKittenConnectionPlugin;
import de.safespacegerman.spacekitten.server.routing.RouteExecutor;
import de.safespacegerman.spacekitten.types.RequestData;
import de.safespacegerman.spacekitten.types.data.ServerInfoData;
import de.safespacegerman.spacekitten.types.payloads.DataPayload;
import de.safespacegerman.spacekitten.types.payloads.Payload;
import org.bukkit.Bukkit;
import org.glassfish.grizzly.http.Method;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.server.routes:InfoRoute
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 27.03.2023
 */
public class InfoRoute extends RouteExecutor {

    private SpaceKittenConnectionPlugin plugin;

    public InfoRoute(SpaceKittenConnectionPlugin plugin) {
        super("/info");
        this.plugin = plugin;
    }

    @Override
    protected Payload execute(RequestData requestData) {
        try {
            if (!requestData.method().equals(Method.POST)) return Payload.methodNotAllowed();

            JsonObject data = requestData.data();

            if (data.get("authorization") == null && requestData.request().getAuthorization() == null) return Payload.unauthorized();
            String auth = plugin.getConfig().getString("internalAuth");
            String givenAuth = requestData.request().getAuthorization();
            if (givenAuth == null) {
                givenAuth = data.get("authorization").getAsString();
            }
            if (!givenAuth.equals(auth)) return Payload.unauthorized();

            return DataPayload.create(new ServerInfoData(Bukkit.getServer()));
        } catch (Exception e) {
            e.printStackTrace();
            return Payload.internalServerError();
        }
    }

}
