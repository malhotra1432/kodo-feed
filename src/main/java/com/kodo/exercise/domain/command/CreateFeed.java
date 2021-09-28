package com.kodo.exercise.domain.command;

import com.kodo.exercise.domain.FeedState;
import com.kodo.exercise.domain.value.DateLastEdited;
import com.kodo.exercise.domain.value.Description;
import com.kodo.exercise.domain.value.Image;
import com.kodo.exercise.domain.value.Name;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CreateFeed {
  @NonNull Name name;
  @NonNull Image image;
  @NonNull Description description;
  @NonNull DateLastEdited dateLastEdited;

  public FeedState toFeedState() {
    return FeedState.builder()
        .name(name)
        .image(image)
        .description(description)
        .dateLastEdited(dateLastEdited)
        .build();
  }
}
