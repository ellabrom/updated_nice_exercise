package com.nice.file_processor.models;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class OutputEvent {
    private Long segmentId;
    private EmotionEnum emotion;
    private String speechText;
    private EvenOddEnum evenOrOdd;
}
