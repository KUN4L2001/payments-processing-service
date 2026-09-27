package com.cpt.payments_processing_service.ratelimit;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import java.time.Duration;

public class RateLimiter {

  private final Bucket bucket;

  public RateLimiter() {
    Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1)));
    this.bucket = Bucket.builder().addLimit(limit).build();
  }

  public boolean allowRequest() {
    return bucket.tryConsume(1);
  }
}
