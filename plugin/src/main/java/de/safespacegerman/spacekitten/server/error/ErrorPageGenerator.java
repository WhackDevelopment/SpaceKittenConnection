package de.safespacegerman.spacekitten.server.error;

import de.safespacegerman.spacekitten.json.GsonConverter;
import de.safespacegerman.spacekitten.types.payloads.DataPayloadImpl;
import de.safespacegerman.spacekitten.types.payloads.Payload;
import org.glassfish.grizzly.http.server.Request;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.server.error:ErrorPageGenerator
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public class ErrorPageGenerator implements org.glassfish.grizzly.http.server.ErrorPageGenerator {

    @Override
    public String generate(Request request, int errorCode, String s, String s1, Throwable throwable) {
        switch(errorCode) {
            case 404 -> {
                return GsonConverter.toJsonString(Payload.notFound());
            }
            case 500 -> {
                return GsonConverter.toJsonString(new DataPayloadImpl(Payload.internalServerError(s), s1));
            }
            default -> {
                return null;
            }
        }
    }
}

