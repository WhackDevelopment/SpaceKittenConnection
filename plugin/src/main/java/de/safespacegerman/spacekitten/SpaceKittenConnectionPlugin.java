package de.safespacegerman.spacekitten;

import de.safespacegerman.spacekitten.server.ServerThread;
import de.safespacegerman.spacekitten.server.error.ErrorPageGenerator;
import de.safespacegerman.spacekitten.server.routes.IndexRoute;
import de.safespacegerman.spacekitten.server.routes.InfoRoute;
import de.safespacegerman.spacekitten.server.routes.PingRoute;
import de.safespacegerman.spacekitten.server.routes.WhitelistRoute;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten:SpaceKittenConnectionPlugin
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public class SpaceKittenConnectionPlugin extends JavaPlugin {
    public static long lastRestart = 0L;
    private static SpaceKittenConnectionPlugin instance;
    private BukkitRestApiProviderImpl restApiProvider;
    private ServerThread serverThread;

    public static SpaceKittenConnectionPlugin getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        lastRestart = System.currentTimeMillis();
        this.saveDefaultConfig();
        this.reloadConfig();

        this.restApiProvider = new BukkitRestApiProviderImpl(this);
        BukkitRestApi.setProvider(this.restApiProvider);
        Bukkit.getServicesManager().register(BukkitRestApiProvider.class, this.restApiProvider, this, ServicePriority.High);

        if(!this.getConfig().getBoolean("debug", false)) {
            // async disable grizzly and glassfish debug logging
            new Thread(() -> {
                this.getLogger().info("§6Disabled HttpServer debug Logging...");
                Enumeration<String> loggers = LogManager.getLogManager().getLoggerNames();
                while (loggers.hasMoreElements()) {
                    String loggerName = loggers.nextElement();
                    if (loggerName.toLowerCase().contains("glassfish")) {
                        Logger logger = LogManager.getLogManager().getLogger(loggerName);
                        logger.setLevel(Level.OFF);
                    } else if (loggerName.toLowerCase().contains("grizzly")) {
                        Logger logger = LogManager.getLogManager().getLogger(loggerName);
                        logger.setLevel(Level.OFF);
                    }
                }
            }, "Kill Grizzly Debug Logger").start();
        }

        Thread initializer = new Thread(() -> {
            boolean debug = instance.getConfig().getBoolean("debug", false);

            instance.serverThread = new ServerThread(instance, instance.restApiProvider, "SpaceKittenConnectionPlugin-WebServer");

            instance.serverThread.getServer().addRouteExecutor(new IndexRoute(instance));
            instance.serverThread.getServer().addRouteExecutor(new InfoRoute(instance));
            instance.serverThread.getServer().addRouteExecutor(new PingRoute(instance));
            instance.serverThread.getServer().addRouteExecutor(new WhitelistRoute(instance));

            instance.serverThread.getServer().getHttpServer().getServerConfiguration().setDefaultErrorPageGenerator(new ErrorPageGenerator());

            try {
                instance.serverThread.start();
            } catch(Exception exception) {
                exception.printStackTrace();
                instance.getLogger().warning("Could not run Server Thread... Disabling Plugin...");
                Bukkit.getPluginManager().disablePlugin(instance);
            }
        }, "SpaceKittenConnectionPlugin-Initialization");
        initializer.start();
    }

    @Override
    public void onDisable() {
        try {
            this.serverThread.getServer().stop();
            this.getLogger().info("§aAPI WebServer Shutdown successful...");
            if(this.serverThread.isAlive()) this.serverThread.interrupt();
            this.serverThread = null;
        } catch(Exception exception) {
            exception.printStackTrace();
            this.getLogger().warning("§cAPI WebServer Shutdown failed...");
        }
        this.getLogger().info("§aDisabled SpaceKittenConnectionPlugin successful...");
    }

    public BukkitRestApiProvider getRestApiProvider() {
        return restApiProvider;
    }

    public ServerThread getServerThread() {
        return serverThread;
    }
}
