package com.intentbi.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.intentbi.Entity.Products;
import com.intentbi.Service.ProductHelper;
import com.intentbi.Service.ProductsService;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductsController {
	
	@Autowired
	private ProductsService productsService;
	
	@PostMapping("/addAll")
	public ResponseEntity<List<Products>> saveAllProducts(@RequestParam("file") MultipartFile file) throws IOException{
		
		if(ProductHelper.checkFormat(file)) {
			return new ResponseEntity<List<Products>>(productsService.saveAllProducts(file), HttpStatus.ACCEPTED);
		}
		else {
			throw new IOException("Wrong File type Exception");
		}
		
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Products>> getAllProducts(){
		return new ResponseEntity<List<Products>>(productsService.getAllProducts(), HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<Products> addProducts(@RequestBody Products product){
		return new ResponseEntity<Products>(productsService.addProducts(product), HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Products> updateProducts(@RequestBody Products product){
		return new ResponseEntity<Products>(productsService.updateProducts(product), HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/delete/{productid}")
	public ResponseEntity<Products> deleteProducts(@PathVariable("productid") int productid){
		return new ResponseEntity<Products>(productsService.deleteProducts(productid), HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Products>> getAllByPaginationAndSorting(
					@RequestParam(value = "pageNumber", defaultValue ="0",required = false) Integer pageNumber,
					@RequestParam(value = "pageSize", defaultValue ="10",required = false) Integer pageSize,
					@RequestParam(value = "sortBy", defaultValue ="id",required = false) String sortBy,
					@RequestParam(value = "sortDir", defaultValue ="asc",required = false) String sortDir){
		
		return new ResponseEntity<List<Products>>(productsService.getAllByPaginationAndSorting(pageNumber,pageSize,sortBy,sortDir), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getTotal")
	public ResponseEntity<Integer> getTotalProducts(){
		return new ResponseEntity<Integer>(productsService.getTotalElements(), HttpStatus.ACCEPTED);
	}
	
}
