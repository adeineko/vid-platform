package be.kdg.project.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("d-MMM-yyyy hh:mm");
    @Override
    public JsonElement serialize(LocalDateTime localDateTime,
                                 Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(FORMATTER.format(localDateTime));
    }
}
