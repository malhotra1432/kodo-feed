package com.kodo.exercise.domain.service;

import com.kodo.exercise.api.model.FeedResponse;
import com.kodo.exercise.domain.Feed;
import com.kodo.exercise.domain.command.CreateFeed;
import com.kodo.exercise.domain.repository.FeedDomainRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FeedService {
  private final FeedDomainRepository feedDomainRepository;

  public FeedService(FeedDomainRepository feedDomainRepository) {
    this.feedDomainRepository = feedDomainRepository;
  }

  public FeedResponse fetchFeeds(String text, Pageable pageable) {
    try {
      if (text == null) {
        return feedDomainRepository.findAll(pageable);
      } else {
        return feedDomainRepository.findByNameContainingOrDescriptionContaining(
            text, text, pageable);
      }
    } catch (Exception e) {
      throw new RuntimeException("Unable to fetch data: {}", e);
    }
  }

  public void storeFeedData(List<CreateFeed> feeds) {
    List<Feed> feedList = new ArrayList<>();
    for (CreateFeed feed : feeds) {
      feedList.add(Feed.create(feed));
    }
    try {
      feedDomainRepository.saveAll(feedList);
    } catch (Exception e) {
      throw new RuntimeException("Unable to store data: {}", e);
    }
  }
}
