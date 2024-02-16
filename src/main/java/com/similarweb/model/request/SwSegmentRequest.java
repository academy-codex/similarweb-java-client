package com.similarweb.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwSegmentRequest {
    private String apiKey;
    private String segmentId;
    private String startDate;
    private String endDate;
    private String country;
    private String granularity;

    // Meta Data
    private String alias;
    private String segmentName;
}
