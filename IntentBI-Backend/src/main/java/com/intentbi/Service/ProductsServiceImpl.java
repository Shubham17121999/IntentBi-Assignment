package com.intentbi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.intentbi.Entity.Products;
import com.intentbi.Exception.NoRecordsFoundException;
import com.intentbi.Repository.ProductsRepo;

@Service
public class ProductsServiceImpl implements ProductsService {
	
	@Autowired
	private ProductsRepo productsRepo;
	
	@Override
	public List<Products> saveAllProducts(MultipartFile file) {
		try {
			List<Products> list = ProductHelper.convertExceltoList(file.getInputStream());
			productsRepo.saveAll(list);
			return list;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<Products> getAllProducts() {
		List<Products> li = productsRepo.findAll();
		if(!li.isEmpty()) {
			return li;
		}
		else {
			throw new NoRecordsFoundException("No Records Found");
		}
		
	}

	@Override
	public Products addProducts(Products product) {
		productsRepo.save(product);
		return product;
	}

	@Override
	public Products updateProducts(Products product) {
		Optional<Products> op = productsRepo.findById(product.getId());
		if(op.isPresent()) {
			productsRepo.save(product);
			return product;
		}
		
		throw new NoRecordsFoundException("No Record Found with id:" + product.getId());
	}

	@Override
	public Products deleteProducts(int productId) {
		Optional<Products> op = productsRepo.findById(productId);
		if(op.isPresent()) {
			productsRepo.deleteById(productId);
			return op.get();
		}
		
		throw new NoRecordsFoundException("No Record Found with id:" + productId);
	}

	@Override
	public List<Products> getAllByPaginationAndSorting(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		Sort s = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			s = Sort.by(sortBy).ascending();
		}
		else {
			s = Sort.by(sortBy).descending() ;
		}
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, s);
		
		Page<Products> p = productsRepo.findAll(pageable);
		
		if(p.hasContent()) {
			return p.getContent();
		}
		
		throw new NoRecordsFoundException("No Records Found");
	}

	@Override
	public Integer getTotalElements() {
		List<Products> li = productsRepo.findAll();
		if(!li.isEmpty()) {
			return li.size();
		}
		else {
			throw new NoRecordsFoundException("No Records Found");
		}
	}

}
