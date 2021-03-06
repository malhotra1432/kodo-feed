package com.kodo.exercise.api.controller;

import com.kodo.exercise.api.model.FeedResponse;
import com.kodo.exercise.domain.command.CreateFeed;
import com.kodo.exercise.domain.service.FeedService;
import com.kodo.exercise.message.FeedMessage;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class FeedController {

  private final FeedService feedService;

  public FeedController(FeedService feedService) {
    this.feedService = feedService;
  }

  @GetMapping(value = "/search/feeds")
  public ResponseEntity<FeedResponse> searchFeeds(String search, Pageable pageable) {
    FeedResponse response;
    if (search == null) {
      log.info("Fetching feed data");
      response = feedService.fetchFeeds(pageable);
    } else if (search.startsWith("\"") && search.endsWith("\"")) {
      log.info("Fetching feed data to search {}", search);
      response = feedService.fetchFeedsBasedOnSingleKeyword(search.replace("\"", ""), pageable);
    } else {
      var searchTextSplit = search.split(" ");
      log.info("Fetching feed data to search for list {}", List.of(searchTextSplit));
      response = feedService.fetchFeedsBasedOnMultipleKeywords(List.of(searchTextSplit), pageable);
    }
    return ResponseEntity.ok().body(response);
  }

  @PostMapping("/seed/feeds")
  public ResponseEntity<String> storeFeedData(@RequestBody List<FeedMessage> feeds) {
    log.info("Seeding feed data ");
    List<CreateFeed> createFeedList = new ArrayList<>();
    for (FeedMessage feed : feeds) {
      createFeedList.add(FeedMessage.toCreateFeedCommand(feed));
    }
    feedService.storeFeedData(createFeedList);
    log.info("Feed data seeded");
    return ResponseEntity.accepted().body("Feed data seeded!");
  }
}
