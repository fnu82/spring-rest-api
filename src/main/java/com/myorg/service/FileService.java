package com.myorg.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.myorg.entity.FileDetail;

public interface FileService {
   public FileDetail getFileDetail(long fileId);
   public void saveFile(FileDetail fileDetail);
   public void saveFileToDisk(MultipartFile file);
   public void initializePath();
   public Stream<Path> loadAllFiles() throws IOException;
   public Resource loadAsResource(String filename);
}
