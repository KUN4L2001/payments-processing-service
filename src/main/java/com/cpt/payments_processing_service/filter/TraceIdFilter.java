package com.cpt.payments_processing_service.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
public class TraceIdFilter extends OncePerRequestFilter {

  private static final String TRACE_ID = "traceId";

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String traceId = request.getHeader(TRACE_ID);

    if (traceId == null || traceId.isBlank()) {
      traceId = UUID.randomUUID().toString();
    }

    MDC.put(TRACE_ID, traceId);

    log.info("Trace id: {}", traceId);

    response.setHeader(TRACE_ID, traceId);

    try {
      filterChain.doFilter(request, response);
    } finally {
      // trace id removed after one complete request is done
      MDC.remove(TRACE_ID);
    }
  }
}
