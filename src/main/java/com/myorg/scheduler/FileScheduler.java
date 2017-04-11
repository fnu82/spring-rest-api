package com.myorg.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FileScheduler {
	@Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        System.out.println("hooooooooooooooooooo");
    }
}
