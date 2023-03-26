package de.safespacegerman.spacekitten.server.routes;

import de.safespacegerman.spacekitten.SpaceKittenConnectionPlugin;
import de.safespacegerman.spacekitten.server.routing.RouteExecutor;
import de.safespacegerman.spacekitten.types.RequestData;
import de.safespacegerman.spacekitten.types.payloads.Payload;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.server.routes:IndexRoute
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public class IndexRoute extends RouteExecutor {

    private SpaceKittenConnectionPlugin plugin;

    public IndexRoute(SpaceKittenConnectionPlugin plugin) {
        super("/");
        this.plugin = plugin;
    }

    @Override
    protected Payload execute(RequestData requestData) {
        if (plugin.getConfig().getBoolean("debug", false)) plugin.getLogger().info(requestData.body());
        return Payload.ok();
    }

}
