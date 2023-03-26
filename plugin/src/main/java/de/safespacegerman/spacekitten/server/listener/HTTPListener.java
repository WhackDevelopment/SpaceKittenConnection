package de.safespacegerman.spacekitten.server.listener;

import de.safespacegerman.spacekitten.configuration.ServerConfiguration;
import org.glassfish.grizzly.http.server.NetworkListener;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.server.listener:HTTPListener
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public class HTTPListener extends NetworkListener {

    public HTTPListener(ServerConfiguration config) {
        super("HTTP-NetworkListener", config.getHttpBind().host(), config.getHttpBind().port());
        this.setSecure(false);
        this.setTraceEnabled(false);
    }

}
