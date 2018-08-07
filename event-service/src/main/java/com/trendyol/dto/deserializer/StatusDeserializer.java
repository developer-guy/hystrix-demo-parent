package com.trendyol.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.trendyol.dto.Status;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author Batuhan Apaydın
 */
public class StatusDeserializer extends StdDeserializer<Status> {
    private static final Status DEFAULT_STATUS = Status.UPCOMING;

    public StatusDeserializer() {
        this(null);
    }

    public StatusDeserializer(Class<?> vc) {
        super(vc);
    }

    public Status deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String status = jsonParser.getText();
        if (StringUtils.hasText(status)) {
            return Status.valueOf(status.toUpperCase());
        }
        return DEFAULT_STATUS;
    }
}
