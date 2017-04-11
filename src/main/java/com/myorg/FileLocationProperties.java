package com.myorg;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("com.myorg")
public class FileLocationProperties {
	// change this locaton to your local folder.
	private String location = "c://users//futpal//test";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
