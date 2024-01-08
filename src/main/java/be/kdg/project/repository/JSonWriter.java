package be.kdg.project.repository;

import be.kdg.project.domain.Channel;
import be.kdg.project.domain.Video;
import be.kdg.project.util.LocalDateTimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class JSonWriter {
    private static final String CHANNELS_JSON = "channels.json";
    private static final String VIDEOS_JSON = "videos.json";
    private Gson gson;

    public JSonWriter() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gson = builder.create();
    }

    public void writeChannels(List<Channel> channels){
        String json = gson.toJson(channels);
        try (FileWriter writer = new FileWriter(CHANNELS_JSON)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save channels to JSON", e);
        }
    }

    public void writeVideos(List<Video> videos){
        String json = gson.toJson(videos);
        try (FileWriter writer = new FileWriter(VIDEOS_JSON)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save objects to JSON", e);
        }
    }
}
