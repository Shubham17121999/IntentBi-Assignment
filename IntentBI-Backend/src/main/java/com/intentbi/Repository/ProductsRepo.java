package com.intentbi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intentbi.Entity.Products;

public interface ProductsRepo extends JpaRepository<Products, Integer> {

}
