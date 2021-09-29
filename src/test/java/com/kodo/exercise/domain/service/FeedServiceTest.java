package com.kodo.exercise.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.kodo.exercise.domain.command.CreateFeed;
import com.kodo.exercise.domain.repository.FeedDomainRepository;
import com.kodo.exercise.message.FeedMessage;
import com.kodo.exercise.util.TestDataBuilder;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class FeedServiceTest {

  private final FeedDomainRepository feedDomainRepository = mock(FeedDomainRepository.class);
  private final FeedService feedService = new FeedService(feedDomainRepository);

  @Test
  void shouldCreateFeeds() {
    List<FeedMessage> feedList = List.of(TestDataBuilder.randomFeedMessageBuilder().build());
    List<CreateFeed> createFeedList = new java.util.ArrayList<>();
    for (FeedMessage feedMessage : feedList) {
      createFeedList.add(FeedMessage.toCreateFeedCommand(feedMessage));
    }

    feedService.storeFeedData(createFeedList);

    verify(feedDomainRepository, times(1)).saveAll(any());
  }

  @Test
  void shouldSearchFeeds() {
    String textSearch = "King";
    Pageable pageable = PageRequest.of(0, 10);

    feedService.fetchFeeds(textSearch, pageable);

    verify(feedDomainRepository, times(1))
        .findByNameContainingOrDescriptionContaining(textSearch, textSearch, pageable);
  }
}
