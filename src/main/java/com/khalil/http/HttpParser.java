/*
 * @Author: Khalil Naji 
 * @Date: 2025-03-25 12:51:52 
 * @Last Modified by: Khalil Naji
 * @Last Modified time: 2025-03-25 12:54:00
 */

package com.khalil.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpParser {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class.getName());

    private final static int SP = 0x20; // 32
    private final static int CR = 0x0D; // 13
    private final static int LF = 0x0A; // 10

    public HttpRequest parseHttpRequest(InputStream inputStream) throws HttpParsingException {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);

        HttpRequest request = new HttpRequest();
        try {
            parseRequestLine(reader, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            parseHeaders(reader, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        parseBody(reader, request);

        return request;
    }

    private void parseRequestLine(InputStreamReader reader, HttpRequest request)
            throws IOException, HttpParsingException {
        boolean methodParsed = false;
        boolean requestTargetParsed = false;

        StringBuilder processingDataBuffer = new StringBuilder();
        int _byte;

        while ((_byte = reader.read()) >= 0) {
            if (_byte == CR) {
                _byte = reader.read();
                if (_byte == LF) {

                    LOGGER.debug("Request Line VERSION to Process : {}", processingDataBuffer.toString());

                    if (!methodParsed || !requestTargetParsed) {
                        throw new HttpParsingException(HttpStatusCodes.CLIENT_ERROR_400_BAD_REQUEST);
                    }

                    try {
                        request.setHttpVersion(processingDataBuffer.toString());
                    } catch (BadHttpVersionException e) {
                        throw new HttpParsingException(HttpStatusCodes.CLIENT_ERROR_400_BAD_REQUEST);
                    }
                    return;
                } else {
                    throw new HttpParsingException(HttpStatusCodes.CLIENT_ERROR_400_BAD_REQUEST);
                }
            }

            if (_byte == SP) {
                // TODO Process previous data
                if (!methodParsed) {
                    LOGGER.debug("Request Line METHOD to Process : {}", processingDataBuffer.toString());
                    request.setMethod(processingDataBuffer.toString());
                    methodParsed = true;
                } else if (!requestTargetParsed) {
                    LOGGER.debug("Request Line REQ TARGET to Process : {}", processingDataBuffer.toString());
                    request.setTarget(processingDataBuffer.toString());
                    requestTargetParsed = true;
                } else {
                    throw new HttpParsingException(HttpStatusCodes.CLIENT_ERROR_400_BAD_REQUEST);
                }
                processingDataBuffer.delete(0, processingDataBuffer.length());
            } else {
                processingDataBuffer.append((char) _byte);
                if (!methodParsed) {
                    if (processingDataBuffer.length() > HttpMethod.MAX_LENGTH) {
                        throw new HttpParsingException(
                                HttpStatusCodes.SERVER_ERROR_501_NOT_IMPLEMENTED);
                    }
                }
            }
        }
    }

    private void parseHeaders(InputStreamReader reader, HttpRequest request) throws IOException, HttpParsingException {
        StringBuilder processingDataBuffer = new StringBuilder();
        int _byte;

        while ((_byte = reader.read()) >= 0) {

            if (_byte == CR) {
                _byte = reader.read();
                if (_byte == LF) {
                        // Process the header field
                        processSingleHeaderField(processingDataBuffer, request);
                        processingDataBuffer.delete(0, processingDataBuffer.length());
                        continue;
                } else {
                    throw new HttpParsingException(HttpStatusCodes.CLIENT_ERROR_400_BAD_REQUEST);
                }
            } else {
                processingDataBuffer.append((char) _byte);
            }
        }
    }

    private void processSingleHeaderField(StringBuilder processingDataBuffer, HttpRequest request)
            throws HttpParsingException {
        String rawHeaderField = processingDataBuffer.toString();
        LOGGER.debug(rawHeaderField);
        
        Pattern pattern = Pattern.compile("(^[!#$%&'*+\\-\\.^_`|~0-9A-Za-z]+):\\s*(.*)");
        Matcher matcher = pattern.matcher(rawHeaderField);
        
        if (matcher.matches()) { // Ensure the regex matches before accessing groups
            String fieldName = matcher.group(1).trim().toLowerCase(); // Group 1: Header field name
            String fieldValue = matcher.group(2).trim(); // Group 2: Header field value
    
            request.addHeader(fieldName, fieldValue);
            LOGGER.debug("Header Field Name : {}", fieldName);
            LOGGER.debug("Header Field Value : {}", fieldValue);
        }
    }

    private void parseBody(InputStreamReader reader, HttpRequest request) {

    }

    public HttpParser() {
    }
}
