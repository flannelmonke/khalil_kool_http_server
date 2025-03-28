/*
 * @Author: Khalil Naji 
 * @Date: 2025-03-26 18:19:23 
 * @Last Modified by: Khalil Naji
 * @Last Modified time: 2025-03-26 18:19:46
 * According to HTTP 1.1 documentation 
 * "All general-purpose servers MUST support the methods GET and HEAD.
 * All other methods are OPTIONAL."
 */
package com.khalil.http;

public class HttpRequest extends HttpMessage {

    private HttpMethod method;
    private String requestTarget;
    private String originalHttpVersion; // literal from the request
    private HttpVersion bestCompatibleVersion;

    HttpRequest() {
    }

    public void setHttpVersion(String originalHttpVersion) throws BadHttpVersionException, HttpParsingException {
        this.originalHttpVersion = originalHttpVersion;
        this.bestCompatibleVersion = HttpVersion.getBestCompatibleVersion(originalHttpVersion);
        if (this.bestCompatibleVersion == null) {
            throw new HttpParsingException(HttpStatusCodes.SERVER_ERROR_505_VERSION_NOT_SUPPORTED);
        }
    }

    public HttpMethod getMethod() {
        return method;
    }

    void setMethod(String methodName) throws HttpParsingException {
        for (HttpMethod method : HttpMethod.values()) {
            if (methodName.equals(method.name())) {
                this.method = method;
                return;
            }
        }
        throw new HttpParsingException(
                HttpStatusCodes.SERVER_ERROR_501_NOT_IMPLEMENTED);
    }

    public void setTarget(String requestTarget) throws HttpParsingException {
        if (requestTarget == null || requestTarget.length() == 0) {
            throw new HttpParsingException(HttpStatusCodes.SERVER_ERROR_500_INTERNAL_SERVER_ERROR);
        }
        this.requestTarget = requestTarget;
    }

    public String getRequestTarget() {
        return requestTarget;
    }

    public HttpVersion getBestCompatibleVersion() {
        return bestCompatibleVersion;
    }

    public String getOriginalHttpVersion() {
        return originalHttpVersion;
    }

}
