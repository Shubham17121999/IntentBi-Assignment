package com.data.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.data.dto.DataEntry;
import com.data.dto.Productss;
import com.data.entity.Files;
import com.data.entity.Product;
import com.data.exceptions.FileTypeException;
import com.data.repositry.FilesRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class fileServicesImpl implements fileServices{
	
	@Autowired
	private FilesRepo filesRepo;
	@Autowired
	
	private ProductServices productServices;
	@Override
	public List<Products> UploadFile(MultipartFile file) throws IOException ,FileTypeException{
		if(!file.getContentType().equals("text/plain")) {
			System.out.println(file.getContentType());
		throw new FileTypeException("please select a text or json file");
		}
		ObjectMapper obj=new ObjectMapper();
		DataEntry datafetch=obj.readValue(file.getInputStream(), DataEntry.class);
		
		Files files = new Files();
        files.setFileName(file.getOriginalFilename());
        files.setFileType(file.getContentType());
        files.setFileData(file.getBytes());
        Files uploded=filesRepo.save(files);
		return datafetch.getListings();
	}

	@Override
	public Files getFile(Long id) {
		Files files=filesRepo.findById(id).get();
		return files;
	}
	
	
}
//private String vin;