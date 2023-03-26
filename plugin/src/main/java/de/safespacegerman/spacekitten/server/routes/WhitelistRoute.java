package de.safespacegerman.spacekitten.server.routes;

import com.google.gson.JsonObject;
import de.safespacegerman.spacekitten.SpaceKittenConnectionPlugin;
import de.safespacegerman.spacekitten.server.routing.RouteExecutor;
import de.safespacegerman.spacekitten.types.RequestData;
import de.safespacegerman.spacekitten.types.payloads.DataPayload;
import de.safespacegerman.spacekitten.types.payloads.Payload;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.glassfish.grizzly.http.Method;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.server.routes:WhitelistRoute
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public class WhitelistRoute extends RouteExecutor {
    private SpaceKittenConnectionPlugin plugin;

    public WhitelistRoute(SpaceKittenConnectionPlugin plugin) {
        super("/whitelist/");
        this.plugin = plugin;
    }

    @Override
    protected Payload execute(RequestData requestData) {

        if (!requestData.method().equals(Method.POST)) return Payload.methodNotAllowed();

        JsonObject data = requestData.data();

        if (data.get("authorization") == null && requestData.request().getAuthorization() == null) return Payload.unauthorized();
        String auth = plugin.getConfig().getString("internalAuth");
        String givenAuth = requestData.request().getAuthorization();
        if (givenAuth == null) {
            givenAuth = data.get("authorization").getAsString();
        }
        if (!givenAuth.equals(auth)) return Payload.unauthorized();

        if (data.get("action") == null) return Payload.badRequest("Action required.");
        String action = data.get("action").getAsString();

        switch (action.toLowerCase()) {

            case "add": {
                try {
                    if (data.get("name") == null) return Payload.badRequest("Name required.");
                    OfflinePlayer pl = Bukkit.getOfflinePlayer(data.get("name").getAsString());
                    if(pl.isWhitelisted()) {
                        return Payload.forbidden();
                    }
                    pl.setWhitelisted(true);
                    plugin.getLogger().info(pl.getName() + " wurde auf die Whitelist gesetzt.");
                    return Payload.ok();
                } catch(Exception e) {
                    e.printStackTrace();
                    return Payload.internalServerError();
                }
            }

            case "remove": {
                try {
                    if (data.get("name") == null) return Payload.badRequest("Name required.");
                    OfflinePlayer pl = Bukkit.getOfflinePlayer(data.get("name").getAsString());
                    if(!pl.isWhitelisted()) {
                        return Payload.forbidden();
                    }
                    pl.setWhitelisted(false);
                    plugin.getLogger().info(pl.getName() + " wurde von der Whitelist genommen.");
                    return Payload.ok();
                } catch(Exception e) {
                    e.printStackTrace();
                    return Payload.internalServerError();
                }
            }

            case "list": {
                try {
                    return DataPayload.create(Bukkit.getServer().getWhitelistedPlayers());
                } catch(Exception e) {
                    e.printStackTrace();
                    return Payload.internalServerError();
                }
            }

            default:
                break;
        }

        return Payload.notFound();
    }
}
