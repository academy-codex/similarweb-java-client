package com.similarweb.config;

import feign.Logger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimilarWebClientConfig {
    private int readTimeout = 7 * 1000; // in ms
    private int connectTimeout = 9 * 1000; // in ms
    private Logger.Level logLevel = Logger.Level.FULL;
    private String similarWebUrl = "https://api.similarweb.com";

    private String apiKey;

    // Rate Limit Config
    private int limitForPeriod = 1;
    private int refreshPeriod = 1; // in seconds
    private int timeoutDuration = 20000; // in milli-seconds
}
