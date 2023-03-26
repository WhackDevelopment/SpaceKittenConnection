package de.safespacegerman.spacekitten.types.payloads;

import de.safespacegerman.spacekitten.json.GsonConverter;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.http.util.HttpStatus;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.types.payloads:PayloadImpl
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public class PayloadImpl implements Payload {

    public boolean error;
    public String message;
    public HttpStatus status;

    public PayloadImpl(HttpStatus status, String message, boolean error) {
        this.error = error;
        this.message = message;
        this.status = status;
    }

    @Override
    public boolean error() {
        return error;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus status() {
        return status;
    }

    @Override
    public boolean write(Response response) {
        try {
            response.setStatus(this.status());
            response.setContentType("application/json");
            String json = GsonConverter.toJsonString(this);
            response.setContentLength(json.length());
            response.getWriter().write(json);
        } catch(Exception ex) {
            try {
                DataPayload.internalServerError(ex).write(response);
            } catch(Exception e) {}
            return false;
        }
        return true;
    }

    public boolean writeHTML(Response response) {
        try {
            response.setStatus(this.status());
            response.setContentType(message.startsWith("<") ? "text/html" : "text/plain");
            response.setContentLength(message.length());
            response.getWriter().write(message);
        } catch(Exception ex) {
            try {
                DataPayload.internalServerError(ex).write(response);
            } catch(Exception e) {}
            return false;
        }
        return true;
    }

}
