package com.example.excatch.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author songqd
 * @Date 2019/9/5
 * @Description
 */
@Component
@ConfigurationProperties(prefix = "alarmhelper")
public class AlarmHelperBean {
    private String host;
    private String port;
    private String username;
    private String password;

    public List<String> getReceiveList() {
        return receiveList;
    }

    public void setReceiveList(List<String> receiveList) {
        this.receiveList = receiveList;
    }

    private String protocol;
    private String path;
    private List<String> receiveList;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
