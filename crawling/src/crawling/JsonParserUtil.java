package crawling;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParserUtil {
    public static JsonArray parseJsonData(String jsonData) {
        JsonObject jsonObject = JsonParser.parseString(jsonData)
                .getAsJsonObject()
                .getAsJsonObject("response")
                .getAsJsonObject("body")
                .getAsJsonObject("items");

        return jsonObject.getAsJsonArray("item");
    }
}
