package de.safespacegerman.spacekitten.server.routes;

import de.safespacegerman.spacekitten.SpaceKittenConnectionPlugin;
import de.safespacegerman.spacekitten.server.routing.RouteExecutor;
import de.safespacegerman.spacekitten.types.RequestData;
import de.safespacegerman.spacekitten.types.payloads.Payload;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.server.routes:PingRoute
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public class PingRoute extends RouteExecutor {

    private SpaceKittenConnectionPlugin plugin;

    public PingRoute(SpaceKittenConnectionPlugin plugin) {
        super("/ping");
        this.plugin = plugin;
    }

    @Override
    protected Payload execute(RequestData requestData) {
        if(plugin.getConfig().getBoolean("debug", false)) plugin.getLogger().info(requestData.body());
        return Payload.ok();
    }

}
