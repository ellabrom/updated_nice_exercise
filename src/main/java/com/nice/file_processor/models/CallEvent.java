package com.nice.file_processor.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@AllArgsConstructor
@Data
public class CallEvent {
    @NonNull
    private Long segmentId;
    @NonNull
    private Integer timeDuration;
    @NonNull
    private String startTime;

}
