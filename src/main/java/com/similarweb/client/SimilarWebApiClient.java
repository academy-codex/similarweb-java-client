package com.similarweb.client;

import com.similarweb.config.SimilarWebClientConfig;
import feign.Feign;
import feign.Request;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Objects;

/**
 * This class provides a client for interacting with the SimilarWeb API.
 * It includes methods for retrieving various traffic and engagement metrics for a given domain.
 */
public class SimilarWebApiClient {

    private static final String SW_API_RL = "similarWebRateLimiter";
    /**
     * This method returns a client object for interacting with the SimilarWeb API.
     *
     * @param config The SimilarWebClientConfig containing the configuration options for the client.
     * @return The SWApi client object.
     * @throws IllegalArgumentException if the config is null.
     */
    public static SWApi getSwClient(SimilarWebClientConfig config) {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("Config is null! Cannot instantiate SW Client");
        }

        return feignBuilder(config)
                .target(SWApi.class, config.getSimilarWebUrl());
    }

    /**
     * Returns a Feign Builder object configured with the provided SimilarWebClientConfig.
     * The Feign Builder is responsible for building Feign clients to make API requests.
     *
     * @param config The SimilarWebClientConfig containing the configuration options for the Feign client.
     * @return The Feign Builder object.
     */
    private static Feign.Builder feignBuilder(SimilarWebClientConfig config) {
        return Resilience4jFeign.builder(feignDecorator(config))
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .client(new OkHttpClient())
                .retryer(new NaiveRetryer())
                .logLevel(config.getLogLevel())
                .options(new Request.Options(config.getConnectTimeout(), config.getReadTimeout()));
    }

    /**
     * Returns a {@link FeignDecorators} object configured with the provided {@link SimilarWebClientConfig}.
     *
     * This method creates a {@link RateLimiterConfig} using the specified parameters from the {@link SimilarWebClientConfig}.
     * It then creates a {@link RateLimiterRegistry} and registers a rate limiter with the specified name using the created config.
     * Finally, it builds and returns a {@link FeignDecorators} object configured with the created rate limiter.
     *
     * @param config The {@link SimilarWebClientConfig} containing the configuration options for the feign client.
     * @return The {@link FeignDecorators} object configured with the created rate limiter.
     * @throws IllegalArgumentException if the {@link SimilarWebClientConfig} is null.
     */
    @NotNull
    private static FeignDecorators feignDecorator(@NotNull SimilarWebClientConfig config) {
        RateLimiterConfig rateLimiterConfig = RateLimiterConfig.custom()
                .limitForPeriod(config.getLimitForPeriod())
                .limitRefreshPeriod(Duration.ofSeconds(config.getRefreshPeriod()))
                .timeoutDuration(Duration.ofMillis(config.getTimeoutDuration()))
                .build();

        RateLimiterRegistry registry = RateLimiterRegistry.of(rateLimiterConfig);
        RateLimiter rateLimiter = registry.rateLimiter(SW_API_RL);

        return FeignDecorators.builder()
                .withRateLimiter(rateLimiter)
                .build();
    }

}
