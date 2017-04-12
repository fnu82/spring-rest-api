package com.myorg.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myorg.FileLocationProperties;
import com.myorg.entity.FileDetail;
import com.myorg.exception.FileNotSavedException;
import com.myorg.exception.StorageFileNotFoundException;
import com.myorg.mapper.FileMapper;
import com.myorg.repository.FileRepository;

@Service
public class FileServiceImpl implements FileService{
	private final Path pathLocation;
		
	@Autowired
    FileRepository fileRepository;
	
	public FileServiceImpl(FileLocationProperties properties) {
        this.pathLocation = Paths.get(properties.getLocation());
    }
     
	public FileDetail getFileDetail(long fileId) {
		return fileRepository.findOne(fileId);
	}

	@Override
	public void saveFile(FileDetail fileDetail) {
		 fileRepository.save(FileMapper.populateAuditableFields(fileDetail));
    }

	@Override
	public void saveFileToDisk(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.pathLocation.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			throw new FileNotSavedException();
		}
	}

	@Override
	public void initializePath() {
		try {
			if (Files.notExists(pathLocation)) {
			    Files.createDirectory(pathLocation);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public Resource loadAsResource(String filename) {
		try {
            Path path = pathLocation.resolve(filename);
            Resource resource = new UrlResource(path.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
               throw new StorageFileNotFoundException();

            }
        } catch (MalformedURLException e) {
               throw new StorageFileNotFoundException();
        }
    }
	
}
