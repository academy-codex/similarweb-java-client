package com.similarweb.service;

import com.similarweb.model.SIMILARWEB_METRICS;
import com.similarweb.model.request.SwSegmentRequest;
import com.similarweb.model.segment.SegmentResponse;
import com.similarweb.model.traffic_and_engagement.BaseResponse;
import com.similarweb.model.traffic_and_engagement.unique_visitors.UniqueVisitors;
import com.similarweb.model.traffic_and_engagement.visits.Visits;
import com.similarweb.model.unified.Metric;
import com.similarweb.model.unified.SwResponse;
import org.apache.logging.log4j.util.Strings;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The ResponseWrapper class provides static utility methods for wrapping different types of responses into a unified format.
 * It includes methods for wrapping visits responses, unique visitors responses, and segment responses.
 */
public class ResponseWrapper {
    /**
     * Wraps the given BaseResponse object into a SwResponse object based on the provided SIMILARWEB_METRICS.
     *
     * @param response   The BaseResponse object to be wrapped.
     * @param swMetrics  The SIMILARWEB_METRICS indicating the type of metrics.
     * @return The wrapped SwResponse object.
     * @throws IllegalArgumentException if the provided SIMILARWEB_METRICS is unsupported.
     */
    @NotNull
    public static SwResponse wrap(BaseResponse response, SIMILARWEB_METRICS swMetrics) {
        if (swMetrics.equals(SIMILARWEB_METRICS.VISITS)) {
            return wrapVisitsResponse((Visits) response);
        }
        if (swMetrics.equals(SIMILARWEB_METRICS.UNIQUE_VISITS_DESKTOP)) {
            SwResponse result = wrapUniqueVisitorResponse((UniqueVisitors) response);
            result.setName(swMetrics.getAlias());
            return result;
        }
        if (swMetrics.equals(SIMILARWEB_METRICS.UNIQUE_VISITS_MOBILE)) {
            SwResponse result = wrapUniqueVisitorResponse((UniqueVisitors) response);
            result.setName(swMetrics.getAlias());
            return result;
        }

        throw new IllegalArgumentException(String.format("Unsupported metrics type: %s", swMetrics.getAlias()));

    }

    @NotNull
    private static SwResponse wrapUniqueVisitorResponse(UniqueVisitors uniqueVisitors) {
        SwResponse swResponse = new SwResponse();
        swResponse.setValues(uniqueVisitors.getUnique_visitors().stream()
                .map(visit -> Metric.builder()
                        .date(visit.getDate())
                        .value(visit.getUnique_visitors())
                        .build()
                )
                .collect(Collectors.toList())
        );

        return swResponse;
    }

    @NotNull
    private static SwResponse wrapVisitsResponse(Visits visits) {
        SwResponse swResponse = new SwResponse();
        swResponse.setName(SIMILARWEB_METRICS.VISITS.getAlias());
        swResponse.setValues(visits.getVisits().stream()
                .map(visit -> Metric.builder()
                        .date(visit.getDate())
                        .value(visit.getVisits())
                        .build()
                )
                .collect(Collectors.toList())
        );

        return swResponse;
    }

    @NotNull
    public static SwResponse wrap(SegmentResponse segmentData, SwSegmentRequest request) {
        SwResponse swResponse = new SwResponse();
        swResponse.setName(resolveSegmentName(request));
        swResponse.setValues(segmentData.getSegments()
                .stream()
                .map(segment -> {
                    // If confidence is low, value is NULL and we want to ignore that.
                    if (segment.getVisits() == null) {
                        return null;
                    }

                    return Metric.builder()
                            .date(segment.getDate())
                            .value(segment.getVisits())
                            .build();
                }).filter(Objects::nonNull).collect(Collectors.toList()));

        return swResponse;
    }

    /**
     * Resolves the segment name based on the given request. If the alias is not blank, the alias will be returned.
     * Otherwise, the segment name will be returned.
     *
     * @param request The SwSegmentRequest object containing the segment information
     * @return The resolved segment name
     */
    private static String resolveSegmentName(@NotNull SwSegmentRequest request) {
        if (Strings.isBlank(request.getAlias())) {
            return request.getSegmentName();
        }

        return request.getAlias();
    }
}
