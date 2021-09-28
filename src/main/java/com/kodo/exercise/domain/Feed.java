package com.kodo.exercise.domain;

import com.kodo.exercise.domain.command.CreateFeed;
import lombok.Getter;
import lombok.NonNull;

public class Feed {
  @Getter private final FeedState state;

  public Feed(FeedState feedState) {
    this.state = feedState;
  }

  public static Feed create(@NonNull CreateFeed createFeed) {
    return create(createFeed.toFeedState());
  }

  public static Feed create(@NonNull FeedState feedState) {
    return new Feed(feedState);
  }
}
