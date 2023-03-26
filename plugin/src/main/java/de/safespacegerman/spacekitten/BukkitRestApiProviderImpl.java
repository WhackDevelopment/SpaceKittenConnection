package de.safespacegerman.spacekitten;

import de.safespacegerman.spacekitten.configuration.ServerConfiguration;
import de.safespacegerman.spacekitten.server.WebServer;
import de.safespacegerman.spacekitten.types.HostAndPort;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten:BukkitRestApiProviderImpl
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public class BukkitRestApiProviderImpl implements BukkitRestApiProvider {

    private final WebServer apiWebServer;
    private final ServerConfiguration serverConfiguration;
    private final SpaceKittenConnectionPlugin plugin;

    public BukkitRestApiProviderImpl(SpaceKittenConnectionPlugin plugin) {
        this.plugin = plugin;
        FileConfiguration config = plugin.getConfig();

        this.serverConfiguration = new ServerConfiguration(
                config.getBoolean("debug", false),
                config.getString("hostname", "127.0.0.1"),
                config.getBoolean("hostCheck", false),
                config.getString("storage"),
                new HostAndPort(config.getString("bind", "127.0.0.1"), config.getInt("port", 80))
        );
        this.apiWebServer = new WebServer(this.serverConfiguration);
    }

    public ServerConfiguration getServerConfiguration() {
        return serverConfiguration;
    }

    public WebServer getApiWebServer() {
        return apiWebServer;
    }

    @Override
    public WebServer getServer() {
        return apiWebServer;
    }

}
