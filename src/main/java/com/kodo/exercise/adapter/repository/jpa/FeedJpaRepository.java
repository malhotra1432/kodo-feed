package com.kodo.exercise.adapter.repository.jpa;

import com.kodo.exercise.Entity.FeedEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FeedJpaRepository extends PagingAndSortingRepository<FeedEntity, Long> {}
