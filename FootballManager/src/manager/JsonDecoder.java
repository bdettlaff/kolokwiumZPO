package manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


class JsonDecoder {
    public List<Event> getJsonList() throws IOException {
        Type REVIEW_TYPE = new TypeToken<List<Event>>() {}.getType();
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("/Users/dettlaffb/Desktop/FootballManager/src/event.json"));
        List<Event> data = gson.fromJson(reader, REVIEW_TYPE);
        return data;
    }
}
