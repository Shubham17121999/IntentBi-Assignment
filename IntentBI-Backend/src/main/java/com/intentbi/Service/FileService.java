package com.data.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.data.dto.Products;
import com.data.entity.Files;
import com.data.exceptions.FileTypeException;

public interface fileServices {

	public List<Products> UploadFile(MultipartFile file) throws IOException , FileTypeException;
	
	public Files getFile(Long id);
}