package com.kodo.exercise.message;

import com.kodo.exercise.domain.command.CreateFeed;
import com.kodo.exercise.domain.value.DateLastEdited;
import com.kodo.exercise.domain.value.Description;
import com.kodo.exercise.domain.value.Image;
import com.kodo.exercise.domain.value.Name;
import java.time.Instant;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedMessage {
  @NonNull String name;
  @NonNull String image;
  @NonNull String description;
  @NonNull Instant dateLastEdited;

  public static CreateFeed toCreateFeedCommand(FeedMessage feedMessage) {
    return CreateFeed.builder()
        .name(Name.create(feedMessage.getName()))
        .image(Image.create(feedMessage.getImage()))
        .description(Description.create(feedMessage.getDescription()))
        .dateLastEdited(DateLastEdited.create(feedMessage.getDateLastEdited()))
        .build();
  }
}
