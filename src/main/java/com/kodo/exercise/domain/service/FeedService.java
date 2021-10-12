package com.kodo.exercise.domain.service;

import com.kodo.exercise.adapter.repository.Entity.FeedEntity;
import com.kodo.exercise.adapter.repository.specification.FeedSpecification;
import com.kodo.exercise.api.model.FeedResponse;
import com.kodo.exercise.domain.Feed;
import com.kodo.exercise.domain.command.CreateFeed;
import com.kodo.exercise.domain.repository.FeedDomainRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class FeedService {
  private final FeedDomainRepository feedDomainRepository;

  public FeedService(FeedDomainRepository feedDomainRepository) {
    this.feedDomainRepository = feedDomainRepository;
  }

  public FeedResponse fetchFeeds(Pageable pageable) {
    try {
      return feedDomainRepository.findAll(pageable);
    } catch (Exception e) {
      throw new RuntimeException("Unable to fetch data: {}", e);
    }
  }

  public FeedResponse fetchFeedsBasedOnSingleKeyword(String search, Pageable pageable) {
    try {
      return feedDomainRepository.findByNameContainingOrDescriptionContaining(
          search, search, pageable);
    } catch (Exception e) {
      throw new RuntimeException("Unable to fetch data: {}", e);
    }
  }

  public FeedResponse fetchFeedsBasedOnMultipleKeywords(
      List<String> searchTextList, Pageable pageable) {
    //    Specification<FeedEntity> specification = getFeedEntitySpecification(searchTextList);
    try {
      return feedDomainRepository.findAll(searchTextList, pageable);
    } catch (Exception e) {
      throw new RuntimeException("Unable to fetch data: {}", e);
    }
  }

  private Specification<FeedEntity> getFeedEntitySpecification(List<String> searchTextList) {
    Specification<FeedEntity> specification = null;
    for (var text : searchTextList) {
      var feedEntitySpecification =
          FeedSpecification.nameContains(text).or(FeedSpecification.descriptionContains(text));
      if (specification == null) {
        specification = feedEntitySpecification;
      } else {
        specification = specification.or(feedEntitySpecification);
      }
    }
    return specification;
  }

  public void storeFeedData(List<CreateFeed> feeds) {
    List<Feed> feedList = new ArrayList<>();
    for (var feed : feeds) {
      feedList.add(Feed.create(feed));
    }
    try {
      feedDomainRepository.saveAll(feedList);
    } catch (Exception e) {
      throw new RuntimeException("Unable to store data: {}", e);
    }
  }
}
