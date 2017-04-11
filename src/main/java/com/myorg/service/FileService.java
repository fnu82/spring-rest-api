package com.myorg.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.myorg.entity.FileDetail;

public interface FileService {
   public FileDetail getFileDetail(long fileId);
   public void saveFile(FileDetail fileDetail);
   public void saveFileToDisk(MultipartFile file);
   public void initializePath();
   public Resource loadAsResource(String filename);
}
