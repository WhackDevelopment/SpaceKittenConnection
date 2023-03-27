package de.safespacegerman.spacekitten.types.data;

import de.safespacegerman.spacekitten.SpaceKittenConnectionPlugin;
import de.safespacegerman.spacekitten.types.CurrentOnlinePlayer;
import org.bukkit.Server;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.types.data:ServerInfoData
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 27.03.2023
 */
public class ServerInfoData {

    private final int online;
    private final int maxOnline;
    private final long lastRestartedTimestamp;
    private final List<CurrentOnlinePlayer> onlinePlayers;

    public ServerInfoData(Server server) {
        this.online = server.getOnlinePlayers().size();
        this.maxOnline = server.getMaxPlayers();
        this.lastRestartedTimestamp = SpaceKittenConnectionPlugin.lastRestart;
        this.onlinePlayers = server.getOnlinePlayers().stream().map(CurrentOnlinePlayer::of).collect(Collectors.toList());
    }

}
