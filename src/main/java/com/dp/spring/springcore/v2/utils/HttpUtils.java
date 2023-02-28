package com.dp.spring.springcore.v2.utils;

import com.dp.spring.springcore.v2.exceptions.UnsupportedCallToPrivateConstructor;
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
}
