package com.innogames.tdd_example.service.serializer.time;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Serializes java.time.LocalDateTime into date time string with "yyyy-MM-dd'T'HH:mm:ss'Z'" format
 * The passed LocalDateTime object will therefore be assumed to be in the UTC timezone.
 */
public class ZuluDateTimeSerializer extends StdSerializer<LocalDateTime> {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
      .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

  public ZuluDateTimeSerializer() {
    super(LocalDateTime.class);
  }

  @Override
  public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {
    jsonGenerator.writeString(localDateTime.format(DATE_TIME_FORMATTER));
  }
}