package com.kodo.exercise.adapter.repository.specification;

import com.kodo.exercise.adapter.repository.Entity.FeedEntity;
import org.springframework.data.jpa.domain.Specification;

public class FeedSpecification {
  public static Specification<FeedEntity> nameContains(String searchText) {
    return (root, query, builder) -> {
      var titleLowerCase = builder.lower(root.get("name"));
      return builder.like(titleLowerCase, "%" + searchText.toLowerCase() + "%");
    };
  }

  public static Specification<FeedEntity> descriptionContains(String searchText) {
    return (root, query, builder) -> {
      var titleLowerCase = builder.lower(root.get("description"));
      return builder.like(titleLowerCase, "%" + searchText.toLowerCase() + "%");
    };
  }
}
