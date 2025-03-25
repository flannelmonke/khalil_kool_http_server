/*
 * @Author: Khalil Naji 
 * @Date: 2025-03-25 12:54:39 
 * @Last Modified by:   mikey.zhaopeng 
 * @Last Modified time: 2025-03-25 12:54:39 
 */
package com.khalil.httpserver.Config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.khalil.httpserver.util.Json;

public class configurationManager {

    // this will be a singleton since we only need one configuration manager per
    // server
    private static configurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private configurationManager() {

    }

    public static configurationManager getInstance() {
        if (myConfigurationManager == null)
            myConfigurationManager = new configurationManager();

        return myConfigurationManager;
    }

    /*
     * Used to load a config file by the given path
     */
    @SuppressWarnings("resource")
    public void loadConfigurationFile(String filePath) {

        FileReader fileReader;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }

        StringBuilder sb = new StringBuilder();
        int i;

        try {
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);
            }
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }

        JsonNode conf;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing config file", e);
        }

        try {
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing config file [INTERNAL]", e);
        } catch (IllegalArgumentException e) {
            throw new HttpConfigurationException("Bad input");
        }
    }

    /*
     * returns the current loaded configuration
     */
    public Configuration getCurrentConfiguration() {
        if (myCurrentConfiguration == null) {
            throw new HttpConfigurationException("No current configuration set.");
        }
        return myCurrentConfiguration;

    }
}
