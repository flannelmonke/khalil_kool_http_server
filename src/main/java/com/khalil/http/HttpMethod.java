/*
 * @Author: Khalil Naji 
 * @Date: 2025-03-25 13:12:24 
 * @Last Modified by:   Khalil Naji
 * @Last Modified time: 2025-03-25 13:12:24 
 */
package com.khalil.http;

public enum HttpMethod {
    GET, HEAD;

    public static final int MAX_LENGTH;

    static {
        int tempMaxLength = -1;
        for (HttpMethod method : values()) {
            if (method.name().length() > tempMaxLength) {
                tempMaxLength = method.name().length();
            }
        }
        MAX_LENGTH = tempMaxLength;
    }
}