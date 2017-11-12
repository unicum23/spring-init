package my.study1.converter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class ConverterUtils {

    public static Object keyToObject(JsonElement keyElement) {
        if (keyElement.isJsonPrimitive()) {
            JsonPrimitive primitive = keyElement.getAsJsonPrimitive();
            if (primitive.isNumber()) {
                return primitive.getAsNumber();
            } else if (primitive.isBoolean()) {
                return primitive.getAsBoolean();
            } else if (primitive.isString()) {
                return primitive.getAsString();
            } else {
                throw new AssertionError();
            }
        } else if (keyElement.isJsonNull()) {
            return null;
        } else {
            throw new AssertionError();
        }
    }

}
