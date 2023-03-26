package de.safespacegerman.spacekitten.types.payloads;

import de.safespacegerman.spacekitten.types.Struct;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.http.util.HttpStatus;

import java.io.IOException;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.types.payloads:Payload
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public interface Payload extends Struct {

    boolean error();

    String message();

    HttpStatus status();

    boolean write(Response response) throws IOException;

    static Payload ok() {
        return ok("OK_200");
    }

    static Payload ok(String message) {
        return new PayloadImpl(HttpStatus.OK_200, message, false);
    }

    static Payload accepted() {
        return accepted("ACCEPTED_202");
    }

    static Payload accepted(String message) {
        return new PayloadImpl(HttpStatus.ACCEPTED_202, message, false);
    }

    static Payload badRequest() {
        return badRequest("BAD_REQUEST_400");
    }

    static Payload badRequest(String message) {
        return new PayloadImpl(HttpStatus.BAD_REQUEST_400, message, true);
    }

    static Payload unauthorized() {
        return unauthorized("UNAUTHORIZED_401");
    }

    static Payload unauthorized(String message) {
        return new PayloadImpl(HttpStatus.UNAUTHORIZED_401, message, true);
    }

    static Payload forbidden() {
        return forbidden("FORBIDDEN_403");
    }

    static Payload forbidden(String message) {
        return new PayloadImpl(HttpStatus.FORBIDDEN_403, message, true);
    }

    static Payload notFound() {
        return notFound("NOT_FOUND_404");
    }

    static Payload notFound(String message) {
        return new PayloadImpl(HttpStatus.NOT_FOUND_404, message, true);
    }

    static Payload notImplemented() {
        return notImplemented("NOT_IMPLEMENTED_501");
    }

    static Payload notImplemented(String message) {
        return new PayloadImpl(HttpStatus.NOT_IMPLEMENTED_501, message, true);
    }

    static Payload methodNotAllowed() {
        return methodNotAllowed("METHOD_NOT_ALLOWED_405");
    }

    static Payload methodNotAllowed(String message) {
        return new PayloadImpl(HttpStatus.METHOD_NOT_ALLOWED_405, message, true);
    }

    static Payload internalServerError() {
        return internalServerError("INTERNAL_SERVER_ERROR_500");
    }

    static Payload internalServerError(String message) {
        return new PayloadImpl(HttpStatus.INTERNAL_SERVER_ERROR_500, message, true);
    }

    static Payload badGateway() {
        return badGateway("BAD_GATEWAY_502");
    }

    static Payload badGateway(String message) {
        return new PayloadImpl(HttpStatus.BAD_GATEWAY_502, message, true);
    }

    static void catchException(Response response, Exception e) {
        try {
            DataPayload.internalServerError(e).write(response);
        } catch(Exception ex) {}
    }

}
