
/*
* Author: Khalil Naji
* According to HTTP 1.1 documentation 
* "All general-purpose servers MUST support the methods GET and HEAD.
*  All other methods are OPTIONAL."
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

    void setMethod(HttpMethod method) {
        this.method = method;
    }

}
