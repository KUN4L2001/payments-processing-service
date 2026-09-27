package com.cpt.payments_processing_service.ratelimit;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class RateLimiterService {

  private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

  public boolean allowRequest(String ipAddress) {
    Bucket bucket = buckets.computeIfAbsent(ipAddress, key -> createBucket());
    return bucket.tryConsume(1);
  }

  private Bucket createBucket() {
    Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1)));
    return Bucket.builder().addLimit(limit).build();
  }
}
