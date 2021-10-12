package com.kodo.exercise.adapter.repository;

import com.kodo.exercise.adapter.repository.codec.FeedStateAdapter;
import com.kodo.exercise.api.model.FeedJsonData;
import com.kodo.exercise.api.model.FeedResponse;
import com.kodo.exercise.config.GlobalConfig;
import com.kodo.exercise.domain.Feed;
import com.kodo.exercise.domain.repository.FeedDomainRepository;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class FeedFileRepository implements FeedDomainRepository {
  private final FeedStateAdapter feedStateAdapter;

  public FeedFileRepository(FeedStateAdapter feedStateAdapter) {
    this.feedStateAdapter = feedStateAdapter;
  }

  @Override
  public void saveAll(@NonNull List<Feed> feedList) {}

  @Override
  public FeedResponse findAll(Pageable pageable) {
    return getFeedResponseByPage(pageable);
  }

  @Override
  public FeedResponse findByNameContainingOrDescriptionContaining(
      String name, String description, Pageable pageable) {
    return getFeedResponseByPage(name, description, pageable);
  }

  @Override
  public FeedResponse findAll(List<String> searchTextList, Pageable pageable) {
    return getFeedResponseByPage(searchTextList, pageable);
  }

  private FeedResponse getFeedResponseByPage(String name, String description, Pageable pageable) {
    FeedResponse feedResponse;
    try {
      var feedMessageData = readJsonFileData();
      Predicate<FeedJsonData> listContainsNameOrDescription = toPredicate(name, description);
      var result =
          feedMessageData.stream()
              .filter(listContainsNameOrDescription)
              .collect(Collectors.toList());
      Page<FeedJsonData> page = getFeedDataPage(pageable, result);
      feedResponse = FeedStateAdapter.feedMessageResponseBuilder(page).build();
    } catch (IOException e) {
      feedResponse = null;
      e.printStackTrace();
    }
    return feedResponse;
  }

  private Predicate<FeedJsonData> toPredicate(String name, String description) {
    return feedMessage ->
        feedMessage.getName().contains(name) || feedMessage.getDescription().contains(description);
  }

  private FeedResponse getFeedResponseByPage(List<String> searchTextList, Pageable pageable) {
    FeedResponse feedResponse;
    try {
      var feedMessageData = readJsonFileData();
      List<FeedJsonData> result = new ArrayList<>();
      for (String feedMessageText : searchTextList) {
        Predicate<FeedJsonData> listContainsNameOrDescription =
            toPredicate(feedMessageText, feedMessageText);
        result =
            feedMessageData.stream()
                .filter(listContainsNameOrDescription)
                .collect(Collectors.toList());
      }
      Page<FeedJsonData> page = getFeedDataPage(pageable, result);
      feedResponse = FeedStateAdapter.feedMessageResponseBuilder(page).build();
    } catch (IOException e) {
      feedResponse = null;
      e.printStackTrace();
    }
    return feedResponse;
  }

  private FeedResponse getFeedResponseByPage(Pageable pageable) {
    FeedResponse feedResponse;
    try {
      var feedMessageData = readJsonFileData();
      Page<FeedJsonData> page = getFeedDataPage(pageable, feedMessageData);
      feedResponse = FeedStateAdapter.feedMessageResponseBuilder(page).build();
    } catch (IOException e) {
      feedResponse = null;
      e.printStackTrace();
    }
    return feedResponse;
  }

  private Page<FeedJsonData> getFeedDataPage(
      Pageable pageable, List<FeedJsonData> feedMessageData) {
    int start = (int) pageable.getOffset();
    int end = Math.min((start + pageable.getPageSize()), feedMessageData.size());
    return new PageImpl<>(feedMessageData.subList(start, end), pageable, feedMessageData.size());
  }

  private List<FeedJsonData> readJsonFileData() throws IOException {
    var mapper = GlobalConfig.createObjectMapper();
    InputStream feedStream = this.getClass().getClassLoader().getResourceAsStream("data.json");
    return mapper.readValue(
        feedStream,
        mapper.getTypeFactory().constructCollectionType(List.class, FeedJsonData.class));
  }
}
