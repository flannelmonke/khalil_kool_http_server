package com.khalil.httpserver;

import com.khalil.httpserver.Config.Configuration;
import com.khalil.httpserver.Config.configurationManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Server Starting...");

        configurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = configurationManager.getInstance().getCurrentConfiguration();

        System.out.println("USING PORT: "+conf.getPort());
        System.out.println("USING WEBROOT:"+conf.getWebroot());
    }
}