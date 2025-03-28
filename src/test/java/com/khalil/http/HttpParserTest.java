/*
 * @Author: Khalil Naji 
 * @Date: 2025-03-25 12:54:55 
 * @Last Modified by: Khalil Naji
 * @Last Modified time: 2025-03-26 18:24:34
 */
package com.khalil.http;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.sun.media.jfxmedia.logging.Logger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HttpParserTest {

    private HttpParser httpParser;

    @BeforeAll
    public void beforeClass() {
        httpParser = new HttpParser();
    }

    @Test
    void testParseHttpRequest() {
        HttpRequest request = null;
        try {
            request = httpParser.parseHttpRequest(
                    generateValidGETTestCase());

            assertEquals(request.getMethod(), HttpMethod.GET);
        } catch (HttpParsingException e) {
            fail(e);
        }

        assertNotNull(request);
        assertEquals(request.getMethod(), HttpMethod.GET);
        assertEquals(request.getRequestTarget(), "/");
    }

    @Test
    void testParseHttpRequestBadMethod() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(
                    generateBadTestCaseMethodName());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCodes.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }

    }

    @Test
    void testParseHttpRequestBadMethod1() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(
                    generateBadTestCaseMethodName1());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCodes.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }

    }

    @Test
    void testParseHttpRequestInvalidNumberOfItems() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(
                    generateBadTestCaseRequestLineInvalidNumberOfItems());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCodes.CLIENT_ERROR_400_BAD_REQUEST);
        }

    }

    @Test
    void testParseHttpRequestInvalidEmptyRequestLine() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(
                    generateBadTestCaseEmptyRequestLine());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCodes.CLIENT_ERROR_400_BAD_REQUEST);
        }

    }

    @Test
    void testParseHttpRequestOnlyCRnoLF() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(
                    generateBadTestCaseRequestOnlyCRnoLF());
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(), HttpStatusCodes.CLIENT_ERROR_400_BAD_REQUEST);
        }

    }

    private InputStream generateValidGETTestCase() {
        String rawData = "GET / HTTP/1.1\r\n" + //
                "Host: localhost:8080";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII));
        return inputStream;
    }

    private InputStream generateBadTestCaseMethodName() {
        String rawData = "GeT / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Accept-Language: en-CA,en-US;q=0.7,en;q=0.3\r\n" +
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII));
        return inputStream;
    }

    private InputStream generateBadTestCaseMethodName1() {
        String rawData = "GETTTT / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Accept-Language: en-CA,en-US;q=0.7,en;q=0.3\r\n" +
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII));
        return inputStream;
    }

    private InputStream generateBadTestCaseRequestLineInvalidNumberOfItems() {
        String rawData = "GET / AAAAA / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Accept-Language: en-CA,en-US;q=0.7,en;q=0.3\r\n" +
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII

                )

        );
        return inputStream;
    }

    private InputStream generateBadTestCaseEmptyRequestLine() {
        String rawData = "\r\n" +
                "Host: localhost:8080\r\n" +
                "Accept-Language: en-CA,en-US;q=0.7,en;q=0.3\r\n" +
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII

                )

        );
        return inputStream;
    }

    private InputStream generateBadTestCaseRequestOnlyCRnoLF() {
        String rawData = "GET / AAAAA / HTTP/1.1\r" + // <----- no LF
                "Host: localhost:8080\r\n" +
                "Accept-Language: en-CA,en-US;q=0.7,en;q=0.3\r\n" +
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII

                )

        );
        return inputStream;
    }

}
