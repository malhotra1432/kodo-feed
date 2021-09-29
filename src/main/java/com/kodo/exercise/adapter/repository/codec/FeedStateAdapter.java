package com.kodo.exercise.adapter.repository.codec;

import com.kodo.exercise.adapter.repository.Entity.FeedEntity;
import com.kodo.exercise.api.model.FeedData;
import com.kodo.exercise.api.model.FeedMetaData;
import com.kodo.exercise.api.model.FeedResponse;
import com.kodo.exercise.domain.FeedState;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class FeedStateAdapter {
  public FeedEntity encode(FeedState feedState) {
    return FeedEntity.builder()
        .name(feedState.getName().getName())
        .image(feedState.getImage().getImage())
        .description(feedState.getDescription().getDescription())
        .dateLastEdited(feedState.getDateLastEdited().getDateLastEdited())
        .build();
  }

  public FeedResponse decode(Page<FeedEntity> response) {
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
