package com.khalil.httpserver.core.io;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebRootHandlerTest {

    WebRootHandler webRootHandler;

    private Method CheckIfEndsWithSlash;

    @BeforeAll
    public void beforeClass() throws WebRootNotFoundException, NoSuchMethodException {
        webRootHandler = new WebRootHandler("WebRoot");
        Class<WebRootHandler> cls = WebRootHandler.class;

        CheckIfEndsWithSlash = cls.getDeclaredMethod("CheckIfEndsWithSlash", String.class);
        CheckIfEndsWithSlash.setAccessible(true);
    }

    @Test
    void constructorGoodPath() {
        try {
            WebRootHandler webRootHandler = new WebRootHandler(
                    "D:\\Action\\khalil-kool-http-server\\khalil_kool_http_server\\src\\main\\java\\com\\khalil\\httpserver\\core\\io");
        } catch (WebRootNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void constructorBadPath() {
        try {
            WebRootHandler webRootHandler = new WebRootHandler(
                    "D:\\Action\\khalil-kool-http-server\\khalil_kool_http_server\\src\\main\\java\\com\\khalil\\httpserver\\Awesome_Stuff_That_Does_Not_Exist");
            fail("Expected WebRootNotFoundException to be thrown");
        } catch (WebRootNotFoundException e) {
        }
    }

    @Test
    void constructorGoodRelativePath() {
        try {
            WebRootHandler webRootHandler = new WebRootHandler(
                    "WebRoot");
        } catch (WebRootNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void constructorBadRelativePath() {
        try {
            WebRootHandler webRootHandler = new WebRootHandler(
                    "WebRoot2");
            fail("Expected WebRootNotFoundException to be thrown");
        } catch (WebRootNotFoundException e) {
        }
    }

    @Test
    void CheckIfEndsWithSlashMethodFalse() {
        try {
            boolean result = (Boolean) CheckIfEndsWithSlash.invoke(webRootHandler, "index.html");
            assertFalse(result);
        } catch (IllegalAccessException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        } catch (InvocationTargetException e) {
            fail(e);
        }
    }

    @Test
    void CheckIfEndsWithSlashMethodTrue2() {
        try {
            boolean result = (Boolean) CheckIfEndsWithSlash.invoke(
                    webRootHandler,
                    "/index.html");
            assertFalse(result);
        } catch (IllegalAccessException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        } catch (InvocationTargetException e) {
            fail(e);
        }
    }

    @Test
    void CheckIfEndsWithSlashMethodTrue3() {
        try {
            boolean result = (Boolean) CheckIfEndsWithSlash.invoke(
                    webRootHandler,
                    "/private/index.html");
            assertFalse(result);
        } catch (IllegalAccessException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        } catch (InvocationTargetException e) {
            fail(e);
        }
    }

    @Test
    void CheckIfEndsWithSlashMethodGood() {
        try {
            boolean result = (Boolean) CheckIfEndsWithSlash.invoke(webRootHandler, "index.html");
            assertFalse(result);
        } catch (IllegalAccessException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        } catch (InvocationTargetException e) {
            fail(e);
        }
    }

}
