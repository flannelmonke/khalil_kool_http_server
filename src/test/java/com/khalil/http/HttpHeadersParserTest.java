package com.khalil.http;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HttpHeadersParserTest {

    private HttpParser httpParser;

    private Method parseHeadersMethod;

    @BeforeAll
    public void beforeClass() throws NoSuchMethodException {
        httpParser = new HttpParser();
        Class<HttpParser> cls = HttpParser.class;
        parseHeadersMethod = cls.getDeclaredMethod("parseHeaders", InputStreamReader.class, HttpRequest.class);
        parseHeadersMethod.setAccessible(true);
    }

    @Test
    public void testSimpleSingleHeader() throws InvocationTargetException, IllegalAccessException {
        HttpRequest request = new HttpRequest();
        parseHeadersMethod.invoke(
                httpParser,
                generateSingleHeaderMessage(),
                request);

        assertEquals(1, request.getHeaders().size());
        assertEquals("localhost:8080", request.getHeader("host"));
    }

    @Test
    public void testSimpleMultiHeader() throws InvocationTargetException, IllegalAccessException {
        HttpRequest request = new HttpRequest();
        parseHeadersMethod.invoke(
                httpParser,
                generateMultiHeaderMessage(),
                request);

        assertEquals(12, request.getHeaders().size());
        assertEquals("localhost:8080", request.getHeader("host"));
        assertEquals("keep-alive", request.getHeader("connection"));
    }

    @Test
    public void testSpaceBeforeColonHeader() throws InvocationTargetException, IllegalAccessException {
        HttpRequest request = new HttpRequest();

        try {
            parseHeadersMethod.invoke(
                    httpParser,
                    generateSpaceBeforeColonErorMessage(),
                    request);
        } catch (InvocationTargetException e) {
            if(e.getCause() instanceof HttpParsingException) {
                assertEquals(HttpStatusCodes.CLIENT_ERROR_400_BAD_REQUEST, ((HttpParsingException) e.getCause()).getErrorCode());
            }
        }
    }


    private InputStreamReader generateSingleHeaderMessage() {
        String rawData = "Host: localhost:8080\r\n"; 
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII));
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        return reader;
    }

    private InputStreamReader generateMultiHeaderMessage() {
        String rawData = "Host: localhost:8080\r\n" + //
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:136.0) Gecko/20100101 Firefox/136.0\r\n" + //
                        "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + //
                        "Accept-Language: en-CA,en-US;q=0.7,en;q=0.3\r\n" + //
                        "Accept-Encoding: gzip, deflate, br, zstd\r\n" + //
                        "Connection: keep-alive\r\n" + //
                        "Cookie: Idea-26d0e7c0=44de5b08-32f1-4ffb-adfd-9396a7b2b644; jenkins-timestamper-offseCookie: Idea-26d0e7c0=44de5b08-32f1-4ffb-adfCookie: Idea-26d0e7c0=44de5b08-32f1-4ffb-adfd-9396a7b2b644; jenkins-timestamper-offset=18000000\r\n" + //
                        "Upgrade-Insecure-Requests: 1\r\n" + //
                        "Sec-Fetch-Dest: document\r\n" + //
                        "Sec-Fetch-Mode: navigate\r\n" + //
                        "Sec-Fetch-Site: none\r\n" + //
                        "Sec-Fetch-User: ?1\r\n" + //
                        "Priority: u=0, i"; 
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII));
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        return reader;
    }

    
    private InputStreamReader generateSpaceBeforeColonErorMessage() {
        String rawData = "Host : localhost:8080\r\n"; 
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII));
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        return reader;
    }
}
