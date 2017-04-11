package com.myorg.mapper;

import java.sql.Timestamp;

import org.joda.time.LocalDate;

import com.myorg.entity.FileDetail;

public class FileMapper {

	public static FileDetail populateAuditableFields(FileDetail fileDetail) {
		// TO DO: replace test with actual user logged in 
		fileDetail.setCreatedBy("test");
	    fileDetail.setCreatedDate(new Timestamp(new LocalDate().toDateTimeAtStartOfDay().getMillis()));
	    
	    if(fileDetail.getFileId() > 0) {
	       fileDetail.setModifiedBy("test");
	       fileDetail.setModifiedDate(new Timestamp(new LocalDate().toDateTimeAtStartOfDay().getMillis()));
	    }
		return fileDetail;
		
	}
}
