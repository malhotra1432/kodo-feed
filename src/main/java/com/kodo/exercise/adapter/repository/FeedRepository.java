package com.kodo.exercise.adapter.repository;

import com.kodo.exercise.Entity.FeedEntity;
import com.kodo.exercise.adapter.repository.codec.FeedStateAdapter;
import com.kodo.exercise.adapter.repository.jpa.FeedJpaRepository;
import com.kodo.exercise.domain.Feed;
import com.kodo.exercise.domain.repository.FeedDomainRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
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
}
