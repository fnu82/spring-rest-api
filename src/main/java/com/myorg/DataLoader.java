package com.myorg;

import java.sql.Timestamp;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.myorg.entity.FileDetail;
import com.myorg.repository.FileRepository;
import com.myorg.service.FileService;

@Component
public class DataLoader implements ApplicationRunner{
	@Autowired
	private FileRepository fileRepository;
    
	@Autowired
	private FileService fileService;
	
    @Autowired
    public DataLoader(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		FileDetail f=new FileDetail();
		f.setAuthor("Author1");
		f.setFileName("name1");
		f.setSize(2345);
		f.setCreatedBy("user1");
		Timestamp timestamp = new Timestamp(new LocalDate().toDateTimeAtStartOfDay().getMillis());
		f.setCreatedDate(timestamp);
		fileRepository.save(f);
		
		fileService.initializePath();
	}
}
