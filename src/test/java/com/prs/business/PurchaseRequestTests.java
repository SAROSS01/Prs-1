package com.prs.business;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prs.db.PurchaseRequestRepository;
import com.prs.db.UserRepository;
import com.prs.db.VendorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseRequestTests {
	@Autowired 
	private PurchaseRequestRepository purchaseRequestRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Test
	public void testPurchaseRequestFindAll() {
		Iterable<PurchaseRequest> prs = purchaseRequestRepo.findAll();
		assertNotNull(prs);
	}
	
	@Before
	public void testPurchaseRequestAddAndDelete() {
		User u = userRepo.findById(2).get();
		System.out.println(u);
		PurchaseRequest pr = new PurchaseRequest(0, u, "test", "test", LocalDate.now(), "car", "new", 100, LocalDateTime.now(),null);
		assertNotNull(purchaseRequestRepo.save(pr));
	}
}
