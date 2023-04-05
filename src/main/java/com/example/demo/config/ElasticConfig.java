package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "demo.elastic")
public class ElasticConfig {
	private String certFilePath = "";
	private String login = "";
	private String pw = "";
	private String host = "";
	private int port = 0;
	
	public void setCertFilePath(String certFilePath) {
		this.certFilePath = certFilePath;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getCertFilePath() {
		return this.certFilePath;
	}
	public String getLogin() {
		return this.login;
	}
	public String getPw() {
		return this.pw;
	}
	public String getHost() {
		return this.host;
	}
	public int getPort() {
		return this.port;
	}
}