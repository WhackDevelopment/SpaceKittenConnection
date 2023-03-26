package de.safespacegerman.spacekitten.server;

import de.safespacegerman.spacekitten.configuration.ServerConfiguration;
import de.safespacegerman.spacekitten.server.listener.HTTPListener;
import de.safespacegerman.spacekitten.server.routing.RouteExecutor;
import org.glassfish.grizzly.http.server.HttpServer;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.server.listener:WebServer
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public class WebServer {
    private final ServerConfiguration serverConfiguration;

    private final HttpServer httpServer;

    private org.glassfish.grizzly.http.server.ServerConfiguration sConf;

    public WebServer(ServerConfiguration serverConfiguration) {
        this.serverConfiguration = serverConfiguration;
        this.httpServer = new HttpServer();
        this.httpServer.removeListener("grizzly"); // remove default grizzly listeners
        this.httpServer.addListener(new HTTPListener(serverConfiguration));
        this.sConf = this.httpServer.getServerConfiguration();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> httpServer.shutdownNow()));
    }

    public void start() {
        try {
            this.httpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            this.httpServer.shutdownNow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HttpServer getHttpServer() {
        return httpServer;
    }

    public org.glassfish.grizzly.http.server.ServerConfiguration getConfig() {
        return this.sConf;
    }

    public boolean addRouteExecutor(RouteExecutor executor) {
        try {
            getConfig().addHttpHandler(executor, executor.getName());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ServerConfiguration getServerConfiguration() {
        return serverConfiguration;
    }

}
