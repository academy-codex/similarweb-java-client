package com.similarweb.service;

import com.similarweb.client.SWApi;
import com.similarweb.client.SimilarWebApiClient;
import com.similarweb.config.SimilarWebClientConfig;
import com.similarweb.model.SIMILARWEB_METRICS;
import com.similarweb.model.request.SwSegmentRequest;
import com.similarweb.model.request.SwTrafficRequest;
import com.similarweb.model.traffic_and_engagement.bounce_rate.BounceRates;
import com.similarweb.model.traffic_and_engagement.page_views.PageViews;
import com.similarweb.model.traffic_and_engagement.visit_duration.AvgVisitDurations;
import com.similarweb.model.unified.SwResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The SimilarWebService class provides methods to interact with the SimilarWeb API and retrieve various types of data.
 */
public final class SimilarWebService {
    private static SimilarWebService INSTANCE;
    private SWApi swApi;
    private static Logger log = LoggerFactory.getLogger(SimilarWebService.class);

    private SimilarWebService() {
        this.swApi = SimilarWebApiClient.getSwClient(new SimilarWebClientConfig());
    }

    public static SimilarWebService getInstance() {
        if (INSTANCE == null) {
            synchronized (SimilarWebService.class) {
                if (INSTANCE == null) {
                    log.info("[SimilarWebService] | Initializing service");
                    INSTANCE = new SimilarWebService();
                }
            }
        }

        return INSTANCE;
    }

    /**
     * Fetches segment data based on the provided request.
     *
     * @param request The request object containing the necessary parameters to fetch the segment data.
     *                - apiKey: The API key for authentication.
     *                - segmentId: The ID of the segment to fetch data for.
     *                - startDate: The start date for the data range.
     *                - endDate: The end date for the data range.
     *                - country: The country for which to fetch data (optional).
     *                - granularity: The granularity of the data (optional).
     * @return The response object containing the segment data.
     */
    public SwResponse getSegmentData(SwSegmentRequest request) {
        log.info("[SimilarWebService] | Fetching Segments Data for request: {}", request);
        try {
            return ResponseWrapper.wrap(this.swApi.getSegmentData(request.getApiKey(), request.getSegmentId(), request.getStartDate(),
                    request.getEndDate(), request.getCountry(), request.getGranularity()), request);
        } catch (Exception e) {
            log.error("Error: ", e);
            return null;
        }
    }

    /**
     * Fetches the total traffic and engagement data for a given domain.
     *
     * @param request The request object containing the necessary parameters.
     *                - apiKey: The API key for authentication.
     *                - startDate: The start date for the data range.
     *                - endDate: The end date for the data range.
     *                - country: The country for which to fetch data (optional).
     *                - granularity: The granularity of the data (optional).
     *                - isOnlyMainDomain: Indicates whether to fetch data only for the main domain.
     *                - domain: The domain for which to fetch data.
     * @return The response containing the total traffic and engagement data.
     */
    public SwResponse getTotalTrafficAndEngagement(SwTrafficRequest request) {
        log.info("[SimilarWebService] | Fetching Visits for request: {}", request);
        try {
            if (request.getStartDate() == null && request.getEndDate() == null) {
                log.info("Pulling latest only for Visits!");
                return ResponseWrapper.wrap(this.swApi.getTotalTrafficAndEngagementLatest(request.getApiKey(),
                        request.getCountry(), request.getGranularity(), request.isOnlyMainDomain(),
                        request.getDomain()), SIMILARWEB_METRICS.VISITS);
            } else {
                log.info("Pulling specific dates for Visits!");
                return ResponseWrapper.wrap(this.swApi.getTotalTrafficAndEngagement(request.getApiKey(), request.getStartDate(),
                        request.getEndDate(), request.getCountry(), request.getGranularity(), request.isOnlyMainDomain(),
                        request.getDomain()), SIMILARWEB_METRICS.VISITS);
            }
        } catch (Exception e) {
            log.error("Error: ", e);
            return null;
        }
    }

    /**
     * Fetches the total bounce rates for a given request.
     *
     * @param request The request object containing the necessary parameters.
     *                - apiKey: The API key for authentication.
     *                - startDate: The start date for the data range.
     *                - endDate: The end date for the data range.
     *                - country: The country for which to fetch data (optional).
     *                - granularity: The granularity of the data (optional).
     *                - isOnlyMainDomain: Indicates whether to fetch data only for the main domain.
     *                - domain: The domain for which to fetch data.
     * @return The bounce rates for the given request.
     */
    public BounceRates getTotalBounceRates(SwTrafficRequest request) {
        log.info("[SimilarWebService] | Fetching Bounce Rates for request: {}", request);
        return this.swApi.getTotalBounceRates(request.getApiKey(), request.getStartDate(),
                request.getEndDate(), request.getCountry(), request.getGranularity(), request.isOnlyMainDomain(),
                request.getDomain());
    }

    /**
     * Fetches the total average visit durations for a given traffic request.
     *
     * @param request The traffic request object containing the necessary parameters.
     *                - apiKey: The API key for authentication.
     *                - startDate: The start date for the data range.
     *                - endDate: The end date for the data range.
     *                - country: The country for which to fetch data (optional).
     *                - granularity: The granularity of the data (optional).
     *                - isOnlyMainDomain: Indicates whether to fetch data only for the main domain.
     *                - domain: The domain for which to fetch data.
     * @return The response object containing the total average visit durations.
     */
    public AvgVisitDurations getTotalAvgDurations(SwTrafficRequest request) {
        log.info("[SimilarWebService] | Fetching Average Durations for request: {}", request);
        return this.swApi.getTotalAvgDurations(request.getApiKey(), request.getStartDate(),
                request.getEndDate(), request.getCountry(), request.getGranularity(), request.isOnlyMainDomain(),
                request.getDomain());
    }

    /**
     * Fetches the total page views for a given traffic request.
     *
     * @param request The traffic request object containing the necessary parameters.
     *                - apiKey: The API key for authentication.
     *                - startDate: The start date for the data range.
     *                - endDate: The end date for the data range.
     *                - country: The country for which to fetch data (optional).
     *                - granularity: The granularity of the data (optional).
     *                - isOnlyMainDomain: Indicates whether to fetch data only for the main domain.
     *                - domain: The domain for which to fetch data.
     * @return The response object containing the total page views.
     */
    public PageViews getTotalPageViews(SwTrafficRequest request) {
        log.info("[SimilarWebService] | Fetching Page Views for request: {}", request);
        return this.swApi.getTotalPageViews(request.getApiKey(), request.getStartDate(),
                request.getEndDate(), request.getCountry(), request.getGranularity(), request.isOnlyMainDomain(),
                request.getDomain());
    }

    /**
     * Fetches desktop unique visitors based on the provided request.
     *
     * @param request The request object containing the necessary parameters to fetch desktop unique visitors.
     *                - apiKey: The API key for authentication.
     *                - startDate: The start date for the data range.
     *                - endDate: The end date for the data range.
     *                - country: The country for which to fetch data (optional).
     *                - granularity: The granularity of the data (optional).
     *                - isOnlyMainDomain: Indicates whether to fetch data only for the main domain.
     *                - domain: The domain for which to fetch data.
     * @return The response object containing the desktop unique visitors.
     */
    public SwResponse getDesktopUniqueVisitors(SwTrafficRequest request) {
        log.info("[SimilarWebService] | Fetching Desktop Unique Visitors for request: {}", request);
        try {
            if (request.getStartDate() == null && request.getEndDate() == null) {
                log.info("Pulling latest only for Desktop Unique Visitors!");
                return ResponseWrapper.wrap(this.swApi.getDesktopUniqueVisitorsLatest(request.getApiKey(),
                        request.getCountry(), request.getGranularity(), request.isOnlyMainDomain(),
                        request.getDomain()), SIMILARWEB_METRICS.UNIQUE_VISITS_DESKTOP);
            } else {
                log.info("Pulling specific dates for Desktop Unique Visitors!");
                return ResponseWrapper.wrap(this.swApi.getDesktopUniqueVisitors(request.getApiKey(), request.getStartDate(),
                        request.getEndDate(), request.getCountry(), request.getGranularity(), request.isOnlyMainDomain(),
                        request.getDomain()), SIMILARWEB_METRICS.UNIQUE_VISITS_DESKTOP);
            }
        } catch (Exception e) {
            log.error("Error: ", e);
            return null;
        }
    }

    /**
     * Fetches mobile web unique visitors based on the provided request.
     *
     * @param request The request object containing the necessary parameters to fetch mobile web unique visitors.
     *                - apiKey: The API key for authentication.
     *                - startDate: The start date for the data range.
     *                - endDate: The end date for the data range.
     *                - country: The country for which to fetch data (optional).
     *                - granularity: The granularity of the data (optional).
     *                - isOnlyMainDomain: Indicates whether to fetch data only for the main domain.
     *                - domain: The domain for which to fetch data.
     * @return The response object containing the mobile web unique visitors.
     */
    public SwResponse getMobileWebUniqueVisitors(SwTrafficRequest request) {
        log.info("[SimilarWebService] | Fetching Mobile Web Unique Visitors for request: {}", request);
        try {
            if (request.getStartDate() == null && request.getEndDate() == null) {
                log.info("Pulling latest only for Mobile Web Unique Visitors!");
                return ResponseWrapper.wrap(this.swApi.getMobileWebUniqueVisitorsLatest(request.getApiKey(),
                        request.getCountry(), request.getGranularity(), request.isOnlyMainDomain(),
                        request.getDomain()), SIMILARWEB_METRICS.UNIQUE_VISITS_MOBILE);
            } else {
                log.info("Pulling between specified dates for Mobile Web Unique Visitors!");
                return ResponseWrapper.wrap(this.swApi.getMobileWebUniqueVisitors(request.getApiKey(), request.getStartDate(),
                        request.getEndDate(), request.getCountry(), request.getGranularity(), request.isOnlyMainDomain(),
                        request.getDomain()), SIMILARWEB_METRICS.UNIQUE_VISITS_MOBILE);
            }
        } catch (Exception e) {
            log.error("Error: ", e);
            return null;
        }
    }
}
