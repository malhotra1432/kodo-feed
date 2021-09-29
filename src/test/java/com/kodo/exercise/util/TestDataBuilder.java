package com.kodo.exercise.util;

import com.github.javafaker.Faker;
import com.kodo.exercise.domain.FeedState;
import com.kodo.exercise.domain.command.CreateFeed;
import com.kodo.exercise.domain.value.DateLastEdited;
import com.kodo.exercise.domain.value.Description;
import com.kodo.exercise.domain.value.Image;
import com.kodo.exercise.domain.value.Name;
import com.kodo.exercise.message.FeedMessage;
import java.time.Instant;

public class TestDataBuilder {
  private static final Randomizer randomizer = Randomizer.create();
  private static final Faker faker = Randomizer.create().getFaker();

  public static FeedMessage.FeedMessageBuilder randomFeedMessageBuilder() {
    return FeedMessage.builder()
        .name(faker.funnyName().name())
        .description(faker.howIMetYourMother().character())
        .image(faker.avatar().image())
        .dateLastEdited(Instant.now());
  }

  public static FeedState.FeedStateBuilder randomFeedStateBuilder() {
    return FeedState.builder()
        .name(Name.create(faker.funnyName().name()))
        .description(Description.create(faker.lordOfTheRings().character()))
        .image(Image.create(faker.avatar().image()))
        .dateLastEdited(DateLastEdited.create(Instant.now()));
  }

  public static CreateFeed.CreateFeedBuilder randomCreateFeedBuilder() {
    return CreateFeed.builder()
        .name(Name.create(faker.funnyName().name()))
        .description(Description.create(faker.hitchhikersGuideToTheGalaxy().character()))
        .image(Image.create(faker.avatar().image()))
        .dateLastEdited(DateLastEdited.create(Instant.now()));
  }
}
