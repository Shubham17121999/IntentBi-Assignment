package com.data.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.data.dto.Vehicles;
import com.data.entity.Files;
import com.data.services.fileServices;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

@RestController
@RequestMapping("/files")
public class FileController {
	
	@Autowired
	private fileServices fileServices;
	
	@PostMapping("/upload")
	public ResponseEntity<List<Vehicles>> upload(@RequestParam("file") MultipartFile file) throws StreamReadException, DatabindException, IOException {
		List<Vehicles> list=fileServices.UploadFile(file);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> download(@PathVariable Long id){
		Files file=fileServices.getFile(id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("txt/plain")).body(file.getFileData());
	}
}