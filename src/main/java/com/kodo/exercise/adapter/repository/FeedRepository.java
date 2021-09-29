package com.kodo.exercise.adapter.repository;

import com.kodo.exercise.adapter.repository.Entity.FeedEntity;
import com.kodo.exercise.adapter.repository.codec.FeedStateAdapter;
import com.kodo.exercise.adapter.repository.jpa.FeedJpaRepository;
import com.kodo.exercise.api.model.FeedData;
import com.kodo.exercise.api.model.FeedMetaData;
import com.kodo.exercise.api.model.FeedResponse;
import com.kodo.exercise.domain.Feed;
import com.kodo.exercise.domain.repository.FeedDomainRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
  public FeedResponse findByNameContainingOrDescriptionContaining(
      String name, String description, Pageable pageable) {

    return feedResponse(
        feedJpaRepository.findByNameContainingOrDescriptionContaining(name, description, pageable));
  }

  @Override
  public FeedResponse findAll(Pageable pageable) {
    return feedResponse(feedJpaRepository.findAll(pageable));
  }

  public FeedResponse feedResponse(Page<FeedEntity> response) {
    return feedResponseBuilder(response).build();
  }

  public static FeedResponse.FeedResponseBuilder feedResponseBuilder(Page<FeedEntity> response) {
    var feedEntityList = response.getContent();
    List<FeedData> feedDataList = new ArrayList<>();
    var feedMetaData =
        FeedMetaData.builder()
            .first(response.isFirst())
            .pageNumber(response.getNumber())
            .pageSize(response.getSize())
            .totalPages(response.getTotalPages())
            .totalElements(response.getTotalElements())
            .numberOfElements(response.getNumberOfElements())
            .build();
    for (FeedEntity data : feedEntityList) {
      feedDataList.add(
          FeedData.builder()
              .id(data.getId())
              .name(data.getName())
              .image(data.getImage())
              .description(data.getDescription())
              .dateLastEdited(data.getDateLastEdited())
              .build());
    }
    return FeedResponse.builder().data(feedDataList).meta(feedMetaData);
  }
}
