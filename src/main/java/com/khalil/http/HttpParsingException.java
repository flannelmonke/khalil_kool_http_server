/*
 * @Author: Khalil Naji 
 * @Date: 2025-03-26 18:19:15 
 * @Last Modified by:   Khalil Naji 
 * @Last Modified time: 2025-03-26 18:19:15 
 */
package com.khalil.http;

public class HttpParsingException extends Exception {

    private HttpStatusCodes errorCode;

    public HttpParsingException(HttpStatusCodes errorCode) {
        super(errorCode.MESSAGE);
        this.errorCode = errorCode;
    }

    public HttpStatusCodes getErrorCode() {
        return errorCode;
    }
}
