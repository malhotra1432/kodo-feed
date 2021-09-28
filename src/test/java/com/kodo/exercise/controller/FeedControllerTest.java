package com.kodo.exercise.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.kodo.exercise.domain.command.CreateFeed;
import com.kodo.exercise.domain.service.FeedService;
import com.kodo.exercise.message.FeedMessage;
import com.kodo.exercise.util.TestDataBuilder;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class FeedControllerTest {
  private final FeedService feedService = mock(FeedService.class);
  private final FeedController feedController = new FeedController(feedService);

  @Test
  void shouldSeedFeedData() {
    List<FeedMessage> feeds = new ArrayList<>();
    List<CreateFeed> createFeedList = new ArrayList<>();

    feeds.add(TestDataBuilder.randomFeedMessageBuilder().build());
    for (FeedMessage feed : feeds) {
      createFeedList.add(FeedMessage.toCreateFeedCommand(feed));
    }
    feedController.storeFeedData(feeds);
    verify(feedService).storeFeedData(createFeedList);
  }
}
