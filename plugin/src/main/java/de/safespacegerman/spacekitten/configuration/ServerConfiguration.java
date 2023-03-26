package de.safespacegerman.spacekitten.configuration;

import de.safespacegerman.spacekitten.types.HostAndPort;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.configuration:ServerConfiguration
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public class ServerConfiguration {

    private final boolean debugMode;

    private final String host;
    private final boolean hostChecking;

    private final HostAndPort httpBind;

    private final boolean secure;

    private final String storageMethod;

    public ServerConfiguration(boolean debug, String host, boolean hostChecking, String storageMethod, HostAndPort httpBind) {
        this.debugMode = debug;
        this.host = host;
        this.hostChecking = hostChecking;
        this.storageMethod = storageMethod;
        this.httpBind = httpBind;
        this.secure = false;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public String getHost() {
        return host;
    }

    public String getStorageMethod() {
        return storageMethod;
    }

    public boolean isHostChecking() {
        return hostChecking;
    }

    public HostAndPort getHttpBind() {
        return httpBind;
    }

    public boolean isSecure() {
        return secure;
    }

}
