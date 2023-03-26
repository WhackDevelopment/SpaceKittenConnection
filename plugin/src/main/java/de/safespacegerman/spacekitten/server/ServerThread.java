package de.safespacegerman.spacekitten.server;

import de.safespacegerman.spacekitten.BukkitRestApiProvider;
import de.safespacegerman.spacekitten.SpaceKittenConnectionPlugin;
import org.bukkit.Bukkit;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.server:ServerThread
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public class ServerThread extends Thread {

    private final SpaceKittenConnectionPlugin holder;
    private final BukkitRestApiProvider api;

    public ServerThread(SpaceKittenConnectionPlugin holder, BukkitRestApiProvider api, String threadName) {
        super(threadName);
        this.holder = holder;
        this.api = api;
    }

    @Override
    public void run() {
        try {
            this.api.getServer().start();
        } catch (Exception exception) {
            exception.printStackTrace();
            this.holder.getLogger().warning("Could not run Server... Disabling Plugin...");
            Bukkit.getPluginManager().disablePlugin(this.holder);
        }
    }

    public WebServer getServer() {
        return this.api.getServer();
    }

    public BukkitRestApiProvider getApi() {
        return this.api;
    }


}
