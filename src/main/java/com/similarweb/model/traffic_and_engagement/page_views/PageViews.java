package com.similarweb.model.traffic_and_engagement.page_views;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.similarweb.model.traffic_and_engagement.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageViews extends BaseResponse {
    List<PageView> pages_views;
}
