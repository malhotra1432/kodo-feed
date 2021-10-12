package com.kodo.exercise.adapter.repository;

import com.kodo.exercise.adapter.repository.Entity.FeedEntity;
import com.kodo.exercise.adapter.repository.codec.FeedStateAdapter;
import com.kodo.exercise.adapter.repository.jpa.FeedJpaRepository;
import com.kodo.exercise.api.model.FeedResponse;
import com.kodo.exercise.domain.Feed;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class FeedRepository {
  private final FeedJpaRepository feedJpaRepository;
  private final FeedStateAdapter feedStateAdapter;

  public FeedRepository(FeedJpaRepository feedJpaRepository, FeedStateAdapter feedStateAdapter) {
    this.feedJpaRepository = feedJpaRepository;
    this.feedStateAdapter = feedStateAdapter;
  }

  public void saveAll(@NonNull List<Feed> feedList) {
    List<FeedEntity> feedEntityList = new ArrayList<>();
    for (Feed feed : feedList) {
      feedEntityList.add(feedStateAdapter.encode(feed.getState()));
    }
    feedJpaRepository.saveAll(feedEntityList);
  }

  public FeedResponse findAll(Pageable pageable) {
    return feedStateAdapter.feedResponse(feedJpaRepository.findAll(pageable));
  }

  public FeedResponse findByNameContainingOrDescriptionContaining(
      String name, String description, Pageable pageable) {

    return feedStateAdapter.feedResponse(
        feedJpaRepository.findByNameContainingOrDescriptionContaining(name, description, pageable));
  }

  public FeedResponse findAll(Specification<FeedEntity> specification, Pageable pageable) {
    return feedStateAdapter.feedResponse(feedJpaRepository.findAll(specification, pageable));
  }
}
