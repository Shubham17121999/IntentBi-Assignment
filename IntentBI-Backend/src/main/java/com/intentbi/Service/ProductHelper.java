package com.intentbi.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.intentbi.Entity.Products;

public class ProductHelper {
	
	public static boolean checkFormat(MultipartFile file){
		String contentType = file.getContentType();
		return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}
	
	public static List<Products> convertExceltoList(InputStream is){
		
		List<Products> list = new ArrayList<>();
		
		try {
			
			 XSSFWorkbook workbook = new XSSFWorkbook(is);

	         XSSFSheet sheet = workbook.getSheet("Sheet1");
	         
	         int rowNumber = 0;
	         Iterator<Row> rows = sheet.iterator();
			
	         
	         while(rows.hasNext()) {
	        	 
	        	 Row row = rows.next();
	        	 
	        	 if(rowNumber == 0) {
	        		 rowNumber++;
	        		 continue;
	        	 }
	        	 
	        	 Iterator<Cell> cells = row.iterator();

	        	 int cid = 0;
	        	 
	        	 Products p = new Products();
	        	 
	        	 while(cells.hasNext()) {
	        		 Cell cell = cells.next();
	        		 
	        		 switch(cid) {
		        		 case 0 : p.setMarket(cell.getStringCellValue());
		        		 		  	break;
		        		 case 1 : p.setCountry(cell.getStringCellValue());
		        		 			break;
		        		 case 2 : p.setProduct(cell.getStringCellValue());
		        		 			break;
		        		 case 3 : p.setDiscount(cell.getStringCellValue());
		        		 			break;
		        		 case 4 : p.setUnitSold(cell.getNumericCellValue());
		        		 			break;
		        		 case 5 : p.setManufacturingPrice(cell.getNumericCellValue());
		        		 			break;
		        		 case 6 : p.setSalePrice(cell.getNumericCellValue());
		        		 			break;
		        		 case 7 : p.setGrossSale(cell.getNumericCellValue());
		        		 			break;
		        		 case 8 : p.setSales(cell.getNumericCellValue());
		        		 			break;
		        		 case 9 : p.setCogs(cell.getNumericCellValue());
		        		 			break;
		        		 case 10 : p.setProfit(cell.getNumericCellValue());
		        		 			break;
		        		 case 11 : p.setDate(cell.getDateCellValue());
		        		 			break;
		        		 case 12 : p.setMonth_number((int) cell.getNumericCellValue());
		        		 			break;
		        		 case 13 : p.setMonth_name(cell.getStringCellValue());
		        		 			break;
		        		 case 14 : p.setYear( (int) cell.getNumericCellValue());
		        		 			break;
		        		 default : break;
	        		 }
	        		 cid++;
	        	 }
	        	 list.add(p);
	         }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return list;
	}
}
