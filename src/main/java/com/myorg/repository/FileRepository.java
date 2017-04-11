package com.myorg.repository;

import org.springframework.data.repository.CrudRepository;

import com.myorg.entity.FileDetail;

public interface FileRepository extends CrudRepository<FileDetail, Long>{

}
