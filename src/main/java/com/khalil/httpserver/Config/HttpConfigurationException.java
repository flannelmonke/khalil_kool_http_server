/*
 * @Author: Khalil Naji 
 * @Date: 2025-03-25 12:54:42 
 * @Last Modified by: Khalil Naji
 * @Last Modified time: 2025-03-26 18:20:18
 */
package com.khalil.httpserver.Config;

public class HttpConfigurationException extends RuntimeException {

    public HttpConfigurationException() {
    }

    public HttpConfigurationException(String message) {
        super(message);
    }

    public HttpConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpConfigurationException(Throwable cause) {
        super(cause);
    }

}
