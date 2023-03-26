package de.safespacegerman.spacekitten;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten:BukkitRestApi
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public class BukkitRestApi {

    private static BukkitRestApiProvider registeredService;

    public static BukkitRestApiProvider get() {
        return BukkitRestApi.registeredService;
    }

    static void setProvider(BukkitRestApiProvider registeredService) {
        BukkitRestApi.registeredService = registeredService;
    }

}
