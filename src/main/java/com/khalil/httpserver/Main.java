package com.khalil.httpserver;

import java.io.IOException;
import java.util.logging.Logger;

import com.khalil.httpserver.Config.Configuration;
import com.khalil.httpserver.Config.configurationManager;
import com.khalil.httpserver.core.ServerListenerThread;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    
    
    public static void main(String[] args) {
    
        LOGGER.info("Server Starting...");

        configurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = configurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("USING PORT: "+conf.getPort());
        LOGGER.info("USING WEBROOT:"+conf.getWebroot());

        ServerListenerThread serverListenerThread;
        try {
            serverListenerThread = new ServerListenerThread(conf.getPort(),conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            // TODO handle later
            // e.printStackTrace();
        }
    }
}