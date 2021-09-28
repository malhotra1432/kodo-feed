package com.kodo.exercise.domain.repository;

import com.kodo.exercise.domain.Feed;
import java.util.List;
import lombok.NonNull;

public interface FeedDomainRepository {
  void saveAll(@NonNull List<Feed> feedList);
}
