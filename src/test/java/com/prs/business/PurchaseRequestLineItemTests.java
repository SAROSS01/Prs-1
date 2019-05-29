package com.prs.business;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prs.db.PurchaseRequestLineItemRepository;
import com.prs.db.VendorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseRequestLineItemTests {
	@Autowired 
	private PurchaseRequestLineItemRepository prliRepo;
	
	@Test
	public void testprliFindAll() {
		Iterable<PurchaseRequestLineItem> prlis = prliRepo.findAll();
		assertNotNull(prlis);
	}
	
	@Test
	public void testprliAdd() {
		PurchaseRequest pr = new PurchaseRequest(1, null, null, null, null, null, null, 0, null, null);
		Product product = new Product(1, null, null, null, 0, null, null);
		PurchaseRequestLineItem prli = new PurchaseRequestLineItem(1, pr, product,2);
		int id = prli.getId();
	}
}
