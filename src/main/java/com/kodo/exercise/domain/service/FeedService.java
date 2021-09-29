package com.kodo.exercise.domain.service;

import com.kodo.exercise.domain.Feed;
import com.kodo.exercise.domain.command.CreateFeed;
import com.kodo.exercise.domain.repository.FeedDomainRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FeedService {
  private final FeedDomainRepository feedDomainRepository;

  public FeedService(FeedDomainRepository feedDomainRepository) {
    this.feedDomainRepository = feedDomainRepository;
  }

  public Page fetchFeeds(String name, Pageable pageable) {
    try {
      if (name == null) {
        return feedDomainRepository.findAll(pageable);
      }
      return feedDomainRepository.findByNameContainingOrDescriptionContaining(name, name, pageable);
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
