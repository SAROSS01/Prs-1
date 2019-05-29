package com.prs.business;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prs.db.ProductRepository;
import com.prs.db.VendorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTests {
	@Autowired 
	private ProductRepository productRepo;
	@Autowired
	private VendorRepository vendorRepo; 
	
	@Test
	public void testVendorFindAll() {
		Iterable<Product> products = productRepo.findAll();
		assertNotNull(products);
	}
	
	@Test
	public void testProductAdd() {
		Vendor vendor = new Vendor(2, null, null, null, null, null, null, null, null, false);
		Product p = new Product(0, vendor, "test", "test", 10.10, null, null);
		assertNotNull(productRepo.save(p));
		int id = p.getId();
	}
}
