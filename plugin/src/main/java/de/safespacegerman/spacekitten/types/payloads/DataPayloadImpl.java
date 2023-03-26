package de.safespacegerman.spacekitten.types.payloads;

import de.safespacegerman.spacekitten.json.GsonConverter;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.http.util.HttpStatus;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.types.payloads:DataPayloadImpl
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public class DataPayloadImpl implements DataPayload {

    public Payload parent;
    public Object data;

    public DataPayloadImpl() {
        this.parent = Payload.ok();
        this.data = null;
    }

    public DataPayloadImpl(Object data) {
        this.parent = Payload.ok();
        this.data = data;
    }

    public DataPayloadImpl(Payload payload, Object data) {
        this.parent = payload;
        this.data = data;
    }

    @Override
    public Object data() {
        return data;
    }

    @Override
    public Payload parent() {
        return parent;
    }

    @Override
    public boolean error() {
        return parent().error();
    }

    @Override
    public String message() {
        return parent().message();
    }

    @Override
    public HttpStatus status() {
        return parent().status();
    }

    @Override
    public boolean write(Response response) {
        try {
            response.setStatus(this.status());
            response.setContentType("application/json");
            String json = GsonConverter.toJsonString(this);
            response.setContentLength(json.length());
            response.getWriter().write(json);
        } catch (Exception ex) {
            try {
                DataPayload.internalServerError(ex).write(response);
            } catch (Exception e) {
            }
            return false;
        }
        return true;
    }

}
