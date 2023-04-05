package com.example.demo.obj;

public class CurrentApp {
    private String time;
	private String vid;
    private String packageName;

    public CurrentApp(String time, String vid, String packageName) {
        this.time = time;
        this.vid = vid;
        this.packageName = packageName;
    }
    public CurrentApp() {}
    
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getPackageName() {
        return this.packageName;
    }
    public String getTime() {
        return this.time;
    }
    public String getVid() {
        return this.vid;
    }
}
