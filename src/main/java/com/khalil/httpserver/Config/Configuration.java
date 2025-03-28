/*
 * @Author: Khalil Naji 
 * @Date: 2025-03-25 12:54:36 
 * @Last Modified by: Khalil Naji
 * @Last Modified time: 2025-03-26 18:20:04
 */
package com.khalil.httpserver.Config;

public class Configuration {

    private int port;
    private String webroot;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebroot() {
        return webroot;
    }

    public void setWebroot(String webroot) {
        this.webroot = webroot;
    }

}
