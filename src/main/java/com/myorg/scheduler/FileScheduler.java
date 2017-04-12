package com.myorg.scheduler;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class FileScheduler {
	
	@Value("${file.location}")
	private String location;
	
	@Scheduled(fixedRate = 10000)
  	public void lastFileModified() {
    	Path path = Paths.get(location); 
		Stream<File> recentFile=Arrays.stream(path.toFile().listFiles()).
		                                    filter(f-> f.isFile() && (System.currentTimeMillis() - f.lastModified()) < 10000);
		                                   
		List<File> fileList=recentFile.collect(Collectors.toList());
		if(!CollectionUtils.isEmpty(fileList)){
			System.out.println("send an email");
		}
    }
}
