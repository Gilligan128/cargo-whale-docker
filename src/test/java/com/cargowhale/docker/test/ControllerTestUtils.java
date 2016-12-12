package com.cargowhale.docker.test;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerTestUtils {

    public static void setupMockRequestContextHolder() {
        String localHost = "http://localhost";
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);

        when(httpServletRequestMock.getRequestURL()).thenReturn(new StringBuffer(localHost));
        when(httpServletRequestMock.getRequestURI()).thenReturn(localHost);
        when(httpServletRequestMock.getHeaderNames()).thenReturn(Collections.emptyEnumeration());
        when(httpServletRequestMock.getContextPath()).thenReturn("");
        when(httpServletRequestMock.getServletPath()).thenReturn("");

        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    public static <T> ResponseEntity<T> getForType(final TestRestTemplate restTemplate, final String url, final ParameterizedTypeReference<T> responseType) {
        return restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, responseType);
    }
}
