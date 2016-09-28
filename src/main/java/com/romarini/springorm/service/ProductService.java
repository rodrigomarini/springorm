package com.romarini.springorm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romarini.springorm.dao.ProductDao;
import com.romarini.springorm.model.Product;

@Component
public class ProductService {

	@Autowired
	private ProductDao productDao;

	@Transactional
	public void add(Product product) {
		productDao.persist(product);
	}
	
	@Transactional(readOnly = true)
	public List<Product> listAll() {
		return productDao.findAll();

	}

}