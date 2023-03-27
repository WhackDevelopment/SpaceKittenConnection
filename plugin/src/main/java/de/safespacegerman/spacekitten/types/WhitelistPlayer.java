package de.safespacegerman.spacekitten.types;

import org.bukkit.OfflinePlayer;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.types:WhitelistPlayer
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 26.03.2023
 */
public class WhitelistPlayer {
    private String uuid;
    private String username;

    public WhitelistPlayer(OfflinePlayer pl) {
        this.uuid = pl.getUniqueId().toString();
        this.username = pl.getName();
    }

    public static WhitelistPlayer of(OfflinePlayer pl) {
        return new WhitelistPlayer(pl);
    }

}
