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
    private String httpVersion;

    HttpRequest() {
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

}
