package com.romarini.springorm.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import com.romarini.springorm.model.Product;
import com.romarini.springorm.service.ProductService;

public class SpringOrmMain {
	
	private static Logger logger = LoggerFactory.getLogger(SpringOrmMain.class);
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring.xml");
		
		ProductService productService = ctx.getBean(ProductService.class);
		productService.add(new Product(1, "Big Mac"));
		productService.add(new Product(2, "Big Tasty"));
		
		//antes rollback
		System.out.println("listAll: " + productService.listAll());
		
		try {
			productService.add(new Product(2, "Fritas"));
		} catch (DataAccessException e) {
			logger.info("Problema ao inseir fritas com mesmo id -> executando rollback", e);
		}
		
		//após rollback
		System.out.println("listAll: " + productService.listAll());
		
		ctx.close();
		
	}
}
