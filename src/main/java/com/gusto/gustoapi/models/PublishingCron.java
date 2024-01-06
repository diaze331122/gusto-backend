package com.gusto.gustoapi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Document(collection = "publishing_crons")
public class PublishingCron {

    @Id
    private String id;

    private String log;

    private LocalDateTime startedAt;

    private LocalDateTime updatedAt;
}
