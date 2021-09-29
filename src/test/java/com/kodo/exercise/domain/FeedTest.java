package com.kodo.exercise.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.kodo.exercise.domain.command.CreateFeed;
import com.kodo.exercise.util.TestDataBuilder;
import org.junit.jupiter.api.Test;

class FeedTest {
  @Test
  void shouldCreateFeedStateFromCommand() {
    CreateFeed createFeed = TestDataBuilder.randomCreateFeedBuilder().build();
    var feedState = createFeed.toFeedState();
    assertThat(Feed.create(feedState)).isInstanceOf(Feed.class);
  }
}
