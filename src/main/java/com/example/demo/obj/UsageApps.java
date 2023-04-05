package com.example.demo.obj;

public class UsageApps {
	private String packageName;
	private String totalTime;
	private String firstTime;
	private String lastTime;
	
	public UsageApps(String packageName, String totalTime, String firstTime, String lastTime) {
		this.packageName = packageName;
		this.totalTime = totalTime;
		this.firstTime = firstTime;
		this.lastTime = lastTime;
	}
	
	public String getPackageName() {
		return this.packageName;
	}
	public String getTotalTime() {
		return this.totalTime;
	}
	public String getFirstTime() {
		return this.firstTime;
	}
	public String getLastTime() {
		return this.lastTime;
	}
}
