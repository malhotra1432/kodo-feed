package com.kodo.exercise.adapter.repository.specification;

import static org.mockito.Mockito.*;

import com.kodo.exercise.adapter.repository.Entity.FeedEntity;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.data.jpa.domain.Specification;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FeedSpecificationTest {

  private CriteriaBuilder criteriaBuilderMock;

  private CriteriaQuery criteriaQueryMock;

  private Root<FeedEntity> feedEntityRoot;

  @BeforeAll
  public void setUp() {
    criteriaBuilderMock = mock(CriteriaBuilder.class);
    criteriaQueryMock = mock(CriteriaQuery.class);
    feedEntityRoot = mock(Root.class);
  }

  @Test
  void shouldFetchFeedWithMultipleTexts() {
    var textSearch = "the King";
    var stringArray = textSearch.split(" ");
    Specification<FeedEntity> specification = null;

    for (var text : stringArray) {
      var feedEntitySpecification =
          FeedSpecification.nameContains(text).or(FeedSpecification.descriptionContains(text));
      if (specification == null) {
        specification = feedEntitySpecification;
      } else {
        specification = specification.or(feedEntitySpecification);
      }
      feedEntitySpecification.toPredicate(feedEntityRoot, criteriaQueryMock, criteriaBuilderMock);
    }

    verify(feedEntityRoot, times(stringArray.length)).get("name");
    verify(feedEntityRoot, times(stringArray.length)).get("description");
    verifyNoMoreInteractions(feedEntityRoot);
  }
}
