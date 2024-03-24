package com.intentbi.Entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String market;
	private String country;
	private String product;
	private String discount;
	private Double unitSold; 
	private Double manufacturingPrice;
	private Double salePrice;
	private Double grossSale;
	private Double sales;
	private Double cogs;
	private Double profit;
	private Date date;
	private int month_number;
	private String month_name;
	private int year;
	
}
