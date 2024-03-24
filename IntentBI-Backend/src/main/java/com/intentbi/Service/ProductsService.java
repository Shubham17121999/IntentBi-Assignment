package com.intentbi.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.intentbi.Entity.Products;

public interface ProductsService {
	
	public List<Products> saveAllProducts(MultipartFile file);
	public List<Products> getAllProducts();
	public Products addProducts(Products product);
	public Products updateProducts(Products product);
	public Products deleteProducts(int productId);
	public List<Products> getAllByPaginationAndSorting(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	public Integer getTotalElements();
}
