package com.cpt.payments_processing_service.filter;

import com.cpt.payments_processing_service.ratelimit.RateLimiterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

  private final RateLimiterService rateLimiterService;

  public RateLimitFilter(RateLimiterService rateLimiterService) {
    this.rateLimiterService = rateLimiterService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String ipAddress = request.getRemoteAddr();
    boolean allowed = rateLimiterService.allowRequest(ipAddress);

    if (!allowed) {
      response.setStatus(429);
      response.setContentType("application/json");
      response
          .getWriter()
          .write(
              """
                    {
                        "status": 429,
                        "message": "Too many requests. Please try again later."
                    }
                    """);
      return;
    }

    filterChain.doFilter(request, response);
  }
}
