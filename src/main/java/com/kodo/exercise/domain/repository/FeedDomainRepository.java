package com.kodo.exercise.domain.repository;

import com.kodo.exercise.api.model.FeedResponse;
import com.kodo.exercise.domain.Feed;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;

public interface FeedDomainRepository {
  void saveAll(@NonNull List<Feed> feedList);

  FeedResponse findAll(Pageable pageable);

  FeedResponse findByNameContainingOrDescriptionContaining(
      String name, String description, Pageable pageable);

  //  FeedResponse findAll(Specification<FeedEntity> specification, Pageable pageable);

  FeedResponse findAll(List<String> searchTextList, Pageable pageable);
}
