package com.kodo.exercise.service;

import com.kodo.exercise.Entity.FeedEntity;
import com.kodo.exercise.domain.Feed;
import com.kodo.exercise.domain.command.CreateFeed;
import com.kodo.exercise.domain.repository.FeedDomainRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FeedService {
  private final FeedDomainRepository feedDomainRepository;

  public FeedService(FeedDomainRepository feedDomainRepository) {
    this.feedDomainRepository = feedDomainRepository;
  }

  public void storeFeedData(List<CreateFeed> feeds) {
    List<Feed> feedList = new ArrayList<>();
    for (CreateFeed feed : feeds) {
      feedList.add(Feed.create(feed));
    }
    feedDomainRepository.saveAll(feedList);
  }

  private FeedEntity feedEntityBuilder(CreateFeed feed) {
    return FeedEntity.builder()
        .name(feed.getName().toString())
        .image(feed.getImage().toString())
        .description(feed.getDescription().getDescription())
        .dateLastEdited(feed.getDateLastEdited().getDateLastEdited())
        .build();
  }
}
