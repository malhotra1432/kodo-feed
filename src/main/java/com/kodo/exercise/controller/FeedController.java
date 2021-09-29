package com.kodo.exercise.controller;

import com.kodo.exercise.domain.command.CreateFeed;
import com.kodo.exercise.domain.service.FeedService;
import com.kodo.exercise.message.FeedMessage;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<Page> searchFeeds(String name, Pageable pageable) {
    return ResponseEntity.ok().body(feedService.fetchFeeds(name, pageable));
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
