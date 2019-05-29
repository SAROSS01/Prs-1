package com.prs.business;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prs.db.VendorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VendorTests {
	@Autowired 
	private VendorRepository vendorRepo;
	
	@Test
	public void testVendorFindAll() {
		Iterable<Vendor> vendors = vendorRepo.findAll();
		assertNotNull(vendors);
	}
	
	@Before
	public void testVendorAdd() {
		Vendor v = new Vendor(  0, "tcode", "tname", "taddress","tcity", "ts", "tzip",
				 "tphoneNumber",  "teMail",  true);
		assertNotNull(vendorRepo.save(v));
		int id = v.getId();
	}
	

}
