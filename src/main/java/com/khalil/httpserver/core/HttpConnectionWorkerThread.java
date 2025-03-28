/*
 * @Author: Khalil Naji 
 * @Date: 2025-03-25 12:54:44 
 * @Last Modified by: Khalil Naji
 * @Last Modified time: 2025-03-26 18:20:24
 */
package com.khalil.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpConnectionWorkerThread extends Thread {

    private final Socket socket;
    private final static Logger LOGGER = Logger.getLogger(HttpConnectionWorkerThread.class.getName());

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            String html = "<html><head><title>Simple Java Http server</title></head><body><h1>This page was server using my Simple Java Http Server</h1></body></html>";

            final String CRLF = "\n\r"; // 13, 10 in ASCII

            String response = "HTTP/1.1 200 OK" + CRLF
                    + // Status line : HTTP Version RESPONSE_CODE RESPONSE_MESSAGE
                    "Content-Length:" + html.getBytes().length + CRLF
                    + // HEADER
                    CRLF
                    + html
                    + CRLF + CRLF;

            outputStream.write(response.getBytes());
            LOGGER.info("Connection Processing Finished...");

            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problem with Communication", e);

        } finally {

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }

        }
    }
}
