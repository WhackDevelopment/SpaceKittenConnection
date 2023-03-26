package de.safespacegerman.spacekitten.json;

import com.google.gson.*;

/**
 * SpaceKittenConnection; de.safespacegerman.spacekitten.json:GsonConverter
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 25.03.2023
 */
public class GsonConverter {
    private static Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
    private static Gson gson = new GsonBuilder().create();


    /**
     * Convert any {@link Object} to a Json{@link String}
     * @param toConvert
     * @return
     */
    public static String toJsonString(Object toConvert) {
        try {
            return gson.toJson(toConvert);
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * Convert any {@link Object} to a Json{@link String} with 4 spaces indention
     * @param toConvert
     * @return
     */
    public static String toPrettyJsonString(Object toConvert) {
        try {
            return gsonPretty.toJson(toConvert);
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * Convert a Json{@link String} to any given type
     * @param jsonString
     * @param type
     * @return
     * @param <T>
     */
    public static <T> T toType(String jsonString, Class type) {
        try {
            return (T) gson.fromJson(jsonString,type);
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * Convert a Json{@link String} to a {@link JsonObject}
     * @param jsonString
     * @return
     */
    public static JsonObject toJson(String jsonString) {
        try {
            return JsonParser.parseString(jsonString).getAsJsonObject();
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * Convert a {@link JsonElement} to a Json{@link String}
     * @param jsonElement
     * @return
     */
    public static String toJsonString(JsonElement jsonElement) {
        try {
            return gson.toJson(jsonElement);
        } catch(Exception e) {
            return null;
        }
    }

}
