package de.safespacegerman.spacekitten.types;

import com.google.gson.JsonObject;
import de.safespacegerman.spacekitten.json.GsonConverter;
import org.glassfish.grizzly.Buffer;
import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.server.Request;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.types:RequestData
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public interface RequestData {

    String methodString();

    Method method();

    String action();

    String body();

    JsonObject data();
    Request request();

    class DefaultImpl implements RequestData {

        private Request request;
        private String body;
        private JsonObject data;

        public DefaultImpl(Request request) {
            this.request = request;
            try {
                Buffer postBody = request.getPostBody(4096);
                if (postBody != null) {
                    this.body = postBody.toStringContent();
                } else this.body = "{}";
            } catch (Exception exception) {
                this.body = "{}";
                exception.printStackTrace();
            }
            if (!this.body.startsWith("{")) this.body = "{}";
            try {
                this.data = GsonConverter.toJson(body);
            } catch (Exception e) {
            }
        }

        @Override
        public String methodString() {
            return request.getMethod().toString().toUpperCase();
        }

        @Override
        public Method method() {
            return request.getMethod();
        }

        @Override
        public String action() {
            try {
                return data().get("action").getAsString();
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        public String body() {
            return body;
        }

        @Override
        public JsonObject data() {
            return data;
        }

        @Override
        public Request request() {
            return request;
        }

    }

    static RequestData from(Request request) {
        return new DefaultImpl(request);
    }

}
