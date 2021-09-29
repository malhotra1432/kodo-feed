package com.kodo.exercise.domain.repository;

import com.kodo.exercise.adapter.repository.Entity.FeedEntity;
import com.kodo.exercise.domain.Feed;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeedDomainRepository {
  void saveAll(@NonNull List<Feed> feedList);

  Page<FeedEntity> findByNameContainingOrDescriptionContaining(
      String name, String description, Pageable pageable);

  Page<FeedEntity> findAll(Pageable pageable);
}
