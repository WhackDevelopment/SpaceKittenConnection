package de.safespacegerman.spacekitten.types;

import java.io.Serial;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.types:HostAndPort
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public class HostAndPort implements Struct {

    @Serial
    private static final long serialVersionUID = -3016425142558628141L;
    private final String host;
    private final int port;

    public HostAndPort(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String host() {
        return host;
    }

    public int port() {
        return port;
    }

}
