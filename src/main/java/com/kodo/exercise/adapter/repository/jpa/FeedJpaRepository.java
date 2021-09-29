package com.kodo.exercise.adapter.repository.jpa;

import com.kodo.exercise.adapter.repository.Entity.FeedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FeedJpaRepository extends PagingAndSortingRepository<FeedEntity, Long> {
  public Page<FeedEntity> findByNameContainingOrDescriptionContaining(
      String name, String description, Pageable pageable);

  public Page<FeedEntity> findAll(Pageable pageable);
}
