package com.khalil.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khalil.httpserver.Config.Configuration;
import com.khalil.httpserver.Config.configurationManager;
import com.khalil.httpserver.core.ServerListenerThread;

public class Main {

    public final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Server Starting...");

        configurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = configurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("USING PORT: " + conf.getPort());
        LOGGER.info("USING WEBROOT:" + conf.getWebroot());

        System.out.println("Server Starting...");

        configurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        // Configuration conf =
        // configurationManager.getInstance().getCurrentConfiguration();

        System.out.println("USING PORT: " + conf.getPort());
        System.out.println("USING WEBROOT:" + conf.getWebroot());

        ServerListenerThread serverListenerThread;
        try {
            serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}