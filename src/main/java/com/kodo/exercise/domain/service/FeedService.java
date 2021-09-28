package com.kodo.exercise.domain.service;

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
}
