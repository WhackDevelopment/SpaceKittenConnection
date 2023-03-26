package de.safespacegerman.spacekitten.types.payloads;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.types.payloads:DataPayload
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public interface DataPayload extends Payload {

    Payload parent();

    Object data();

    static DataPayload create(Object data) {
        return new DataPayloadImpl(data);
    }

    static DataPayload create(Payload payload, Object data) {
        return new DataPayloadImpl(payload, data);
    }

    static DataPayload internalServerError(Exception exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        String sStackTrace = sw.toString();
        try {
            sw.close();
            pw.close();
        } catch (Exception e) {
        }
        return new DataPayloadImpl(Payload.internalServerError(exception.getMessage()), sStackTrace);
    }

}
