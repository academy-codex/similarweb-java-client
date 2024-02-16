package com.similarweb.model.traffic_and_engagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {
    private Request request;
    private String status;
    private String last_updated;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Request {
        private String granularity;
        private String format;
        private String domain;
        private String start_date;
        private String end_date;
        private String country;
        private boolean mtd;
        private boolean main_domain_only;
    }
}