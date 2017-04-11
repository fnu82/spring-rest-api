package com.myorg.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FileScheduler {
	@Scheduled(fixedRate = 1000000)
    public void reportCurrentTime() {
       
    }
}
