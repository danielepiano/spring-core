package com.dp.spring.springcore.v2.utils;

import com.dp.spring.springcore.v2.exceptions.UnsupportedCallToPrivateConstructor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Utility methods for HTTP request and responses.
 */
public final class HttpUtils {
    private HttpUtils() {
        throw new UnsupportedCallToPrivateConstructor();
    }

    /**
     * Get the full URI of the current request.
     * @return the full request URI
     */
    public static String getFullURIFromCurrentRequest() {
        return ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
    }

    /**
     * Get the relative URI of the current request, including the query string.
     * @return the relative URI
     */
    public static String getRelativeURIFromCurrentRequest() {
        HttpServletRequest currentRequest = getCurrentRequest();
        if ( currentRequest != null ){
            return currentRequest.getRequestURI() + "?" + currentRequest.getQueryString();
        }
        return null;
    }

    /**
     * Get the current {@link HttpServletRequest} request.
     * @return the current request
     */
    public static HttpServletRequest getCurrentRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes)requestAttributes).getRequest();
        }
        return null;
    }

}
