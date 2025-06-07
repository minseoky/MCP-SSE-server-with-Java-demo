package me.minseoky.mcp_with_spring.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingConfig {

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);    // 쿼리스트링 포함
        filter.setIncludePayload(true);        // 본문 로깅
        filter.setMaxPayloadLength(10000);     // 본문 최대 길이
        filter.setIncludeHeaders(true);        // 헤더 포함 여부
        filter.setAfterMessagePrefix("Incoming Request: ");
        return filter;
    }
}