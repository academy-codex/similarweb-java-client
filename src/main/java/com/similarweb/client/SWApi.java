package com.similarweb.client;

import com.similarweb.model.segment.SegmentResponse;
import com.similarweb.model.traffic_and_engagement.bounce_rate.BounceRates;
import com.similarweb.model.traffic_and_engagement.page_views.PageViews;
import com.similarweb.model.traffic_and_engagement.unique_visitors.UniqueVisitors;
import com.similarweb.model.traffic_and_engagement.visit_duration.AvgVisitDurations;
import com.similarweb.model.traffic_and_engagement.visits.Visits;
import feign.Param;
import feign.RequestLine;

public interface SWApi {
    /**
     * Returns the estimated number of visits to a given domain.
     * All Traffic (desktop + mobile web).
     * */
    @RequestLine("GET /v1/website/{DOMAIN}/total-traffic-and-engagement/visits?api_key={API_KEY}&start_date={START_DATE}&end_date={END_DATE}" +
            "&country={COUNTRY}&granularity={GRANULARITY}&main_domain_only={MAIN_DOMAIN_ONLY}&format=json&show_verified=false&mtd=true")
    Visits getTotalTrafficAndEngagement(@Param("API_KEY") String api_key,
                                        @Param("START_DATE") String startDate,
                                        @Param("END_DATE") String endDate,
                                        @Param("COUNTRY") String country,
                                        @Param("GRANULARITY") String granularity,
                                        @Param("MAIN_DOMAIN_ONLY") boolean isOnlyMainDomain,
                                        @Param("DOMAIN") String domain);

    @RequestLine("GET /v1/website/{DOMAIN}/total-traffic-and-engagement/visits?api_key={API_KEY}" +
            "&country={COUNTRY}&granularity={GRANULARITY}&main_domain_only={MAIN_DOMAIN_ONLY}&format=json")
    Visits getTotalTrafficAndEngagementLatest(@Param("API_KEY") String api_key,
                                        @Param("COUNTRY") String country,
                                        @Param("GRANULARITY") String granularity,
                                        @Param("MAIN_DOMAIN_ONLY") boolean isOnlyMainDomain,
                                        @Param("DOMAIN") String domain);

    /**
     * Returns the bounce rate for a given domain.
     * All Traffic (desktop + mobile web).
     * */
    @RequestLine("GET /v1/website/{DOMAIN}/total-traffic-and-engagement/bounce-rate?api_key={API_KEY}&start_date={START_DATE}&end_date={END_DATE}" +
            "&country={COUNTRY}&granularity={GRANULARITY}&main_domain_only={MAIN_DOMAIN_ONLY}&format=json&show_verified=false&mtd=true")
    BounceRates getTotalBounceRates(@Param("API_KEY") String api_key,
                                             @Param("START_DATE") String startDate,
                                             @Param("END_DATE") String endDate,
                                             @Param("COUNTRY") String country,
                                             @Param("GRANULARITY") String granularity,
                                             @Param("MAIN_DOMAIN_ONLY") boolean isOnlyMainDomain,
                                             @Param("DOMAIN") String domain);

    /**
     * Returns the average visit duration for a given domain (in seconds).
     * All Traffic (desktop + mobile web).
     * */
    @RequestLine("GET /v1/website/{DOMAIN}/total-traffic-and-engagement/average-visit-duration?api_key={API_KEY}&start_date={START_DATE}&end_date={END_DATE}" +
            "&country={COUNTRY}&granularity={GRANULARITY}&main_domain_only={MAIN_DOMAIN_ONLY}&format=json&show_verified=false&mtd=true")
    AvgVisitDurations getTotalAvgDurations(@Param("API_KEY") String api_key,
                                                   @Param("START_DATE") String startDate,
                                                   @Param("END_DATE") String endDate,
                                                   @Param("COUNTRY") String country,
                                                   @Param("GRANULARITY") String granularity,
                                                   @Param("MAIN_DOMAIN_ONLY") boolean isOnlyMainDomain,
                                                   @Param("DOMAIN") String domain);

    /**
     * Returns the estimated number of page views for a domain.
     * All traffic (desktop + mobile web).
     * */
    @RequestLine("GET /v1/website/{DOMAIN}/total-traffic-and-engagement/page-views?api_key={API_KEY}&start_date={START_DATE}&end_date={END_DATE}" +
            "&country={COUNTRY}&granularity={GRANULARITY}&main_domain_only={MAIN_DOMAIN_ONLY}&format=json&show_verified=false&mtd=true")
    PageViews getTotalPageViews(@Param("API_KEY") String api_key,
                                @Param("START_DATE") String startDate,
                                @Param("END_DATE") String endDate,
                                @Param("COUNTRY") String country,
                                @Param("GRANULARITY") String granularity,
                                @Param("MAIN_DOMAIN_ONLY") boolean isOnlyMainDomain,
                                @Param("DOMAIN") String domain);


    /**
     * Returns the daily or monthly unique visitors to a domain on desktop devices.
     * */
    @RequestLine("GET /v1/website/{DOMAIN}/unique-visitors/desktop_unique_visitors?api_key={API_KEY}&start_date={START_DATE}&end_date={END_DATE}" +
            "&country={COUNTRY}&granularity={GRANULARITY}&main_domain_only={MAIN_DOMAIN_ONLY}&format=json&show_verified=false&mtd=true")
    UniqueVisitors getDesktopUniqueVisitors(@Param("API_KEY") String api_key,
                                     @Param("START_DATE") String startDate,
                                     @Param("END_DATE") String endDate,
                                     @Param("COUNTRY") String country,
                                     @Param("GRANULARITY") String granularity,
                                     @Param("MAIN_DOMAIN_ONLY") boolean isOnlyMainDomain,
                                     @Param("DOMAIN") String domain);

    @RequestLine("GET /v1/website/{DOMAIN}/unique-visitors/desktop_unique_visitors?api_key={API_KEY}" +
            "&country={COUNTRY}&granularity={GRANULARITY}&main_domain_only={MAIN_DOMAIN_ONLY}&format=json")
    UniqueVisitors getDesktopUniqueVisitorsLatest(@Param("API_KEY") String api_key,
                                            @Param("COUNTRY") String country,
                                            @Param("GRANULARITY") String granularity,
                                            @Param("MAIN_DOMAIN_ONLY") boolean isOnlyMainDomain,
                                            @Param("DOMAIN") String domain);

    /**
     * Returns the daily or monthly unique visitors to a domain on mobile web.
     * */
    @RequestLine("GET /v1/website/{DOMAIN}/unique-visitors/mobileweb_unique_visitors?api_key={API_KEY}&start_date={START_DATE}&end_date={END_DATE}" +
            "&country={COUNTRY}&granularity={GRANULARITY}&main_domain_only={MAIN_DOMAIN_ONLY}&format=json&show_verified=false&mtd=true")
    UniqueVisitors getMobileWebUniqueVisitors(@Param("API_KEY") String api_key,
                                            @Param("START_DATE") String startDate,
                                            @Param("END_DATE") String endDate,
                                            @Param("COUNTRY") String country,
                                            @Param("GRANULARITY") String granularity,
                                            @Param("MAIN_DOMAIN_ONLY") boolean isOnlyMainDomain,
                                            @Param("DOMAIN") String domain);

    @RequestLine("GET /v1/website/{DOMAIN}/unique-visitors/mobileweb_unique_visitors?api_key={API_KEY}" +
            "&country={COUNTRY}&granularity={GRANULARITY}&main_domain_only={MAIN_DOMAIN_ONLY}&format=json")
    UniqueVisitors getMobileWebUniqueVisitorsLatest(@Param("API_KEY") String api_key,
                                              @Param("COUNTRY") String country,
                                              @Param("GRANULARITY") String granularity,
                                              @Param("MAIN_DOMAIN_ONLY") boolean isOnlyMainDomain,
                                              @Param("DOMAIN") String domain);

    @RequestLine("GET /v1/segment/{SEGMENT_ID}/traffic-and-engagement/query?api_key={API_KEY}&start_date={START_DATE}&end_date={END_DATE}&country={COUNTRY}" +
            "&metrics=visits&granularity={GRANULARITY}&format=json&mtd=true")
    SegmentResponse getSegmentData(@Param("API_KEY") String api_key,
                                   @Param("SEGMENT_ID") String segmentId,
                                   @Param("START_DATE") String startDate,
                                   @Param("END_DATE") String endDate,
                                   @Param("COUNTRY") String country,
                                   @Param("GRANULARITY") String granularity);

}
