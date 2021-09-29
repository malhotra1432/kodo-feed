package com.kodo.exercise.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedMetaData {
  private Integer totalPages;
  private Integer pageNumber;
  private Integer pageSize;
  private long totalElements;
  private Integer numberOfElements;
  private boolean first;
}
