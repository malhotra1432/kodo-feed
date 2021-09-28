package com.kodo.exercise.adapter.repository.Entity;

import java.time.Instant;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Feeds")
public class FeedEntity {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String name;

  @Column private String image;

  @Column(length = 10000)
  private String description;

  @Column private Instant dateLastEdited;
}
