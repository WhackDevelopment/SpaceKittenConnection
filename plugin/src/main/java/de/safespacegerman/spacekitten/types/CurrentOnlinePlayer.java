package de.safespacegerman.spacekitten.types;

import org.bukkit.entity.Player;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.types:CurrentOnlinePlayer
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 27.03.2023
 */
public class CurrentOnlinePlayer {

    private String uuid;
    private String username;
    private String currentWorld;
    private String currentGameMode;
    private int currentPing;
    private int currentLevel;
    private float currentExperience;

    public CurrentOnlinePlayer(Player pl) {
        this.uuid = pl.getUniqueId().toString();
        this.username = pl.getName();
        this.currentGameMode = pl.getGameMode().toString().toLowerCase();
        this.currentWorld = pl.getWorld().getName();
        this.currentPing = pl.spigot().getPing();
        this.currentLevel = pl.getLevel();
        this.currentExperience = pl.getExp();
    }

    public static CurrentOnlinePlayer of(Player pl) {
        return new CurrentOnlinePlayer(pl);
    }

}
