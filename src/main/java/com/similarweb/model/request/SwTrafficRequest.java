package com.similarweb.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwTrafficRequest {
    private String apiKey;
    private String startDate;
    private String endDate;
    private String country;
    private String granularity;
    private boolean isOnlyMainDomain;
    private String domain;
}
