package com.kodo.exercise.util;

import com.kodo.exercise.message.FeedMessage;
import java.time.Instant;

public class TestDataBuilder {

  public static FeedMessage.FeedMessageBuilder randomFeedMessageBuilder() {
    return FeedMessage.builder()
        .name("District Data Liaison")
        .image("http://lorempixel.com/640/480")
        .description(
            "Quis autem quia eos. Similique saepe error qui magnam sint doloremque quo quasi voluptatibus.")
        .dateLastEdited(Instant.now());
  }
}
