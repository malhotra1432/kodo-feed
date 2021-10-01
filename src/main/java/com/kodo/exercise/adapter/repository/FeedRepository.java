package com.kodo.exercise.adapter.repository;

import com.kodo.exercise.adapter.repository.Entity.FeedEntity;
import com.kodo.exercise.adapter.repository.codec.FeedStateAdapter;
import com.kodo.exercise.adapter.repository.jpa.FeedJpaRepository;
import com.kodo.exercise.api.model.FeedResponse;
import com.kodo.exercise.domain.Feed;
import com.kodo.exercise.domain.repository.FeedDomainRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class FeedRepository implements FeedDomainRepository {
  private final FeedJpaRepository feedJpaRepository;
  private final FeedStateAdapter feedStateAdapter;

  public FeedRepository(FeedJpaRepository feedJpaRepository, FeedStateAdapter feedStateAdapter) {
    this.feedJpaRepository = feedJpaRepository;
    this.feedStateAdapter = feedStateAdapter;
  }

  @Override
  public void saveAll(@NonNull List<Feed> feedList) {
    List<FeedEntity> feedEntityList = new ArrayList<>();
    for (Feed feed : feedList) {
      feedEntityList.add(feedStateAdapter.encode(feed.getState()));
    }
    feedJpaRepository.saveAll(feedEntityList);
  }

  @Override
  public FeedResponse findAll(Pageable pageable) {
    return feedStateAdapter.feedResponse(feedJpaRepository.findAll(pageable));
  }

  @Override
  public FeedResponse findByNameContainingOrDescriptionContaining(
      String name, String description, Pageable pageable) {

    return feedStateAdapter.feedResponse(
        feedJpaRepository.findByNameContainingOrDescriptionContaining(name, description, pageable));
  }

  @Override
  public FeedResponse findAll(Specification<FeedEntity> specification, Pageable pageable) {
    return feedStateAdapter.feedResponse(feedJpaRepository.findAll(specification, pageable));
  }
}
