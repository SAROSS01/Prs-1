package com.prs.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.prs.business.JsonResponse;
import com.prs.business.PurchaseRequest;
import com.prs.business.PurchaseRequestLineItem;
import com.prs.db.PurchaseRequestLineItemRepository;
import com.prs.db.PurchaseRequestRepository;


@RestController
@RequestMapping ("/purchase-requests-line-items")
public class PurchaseRequestLineItemController {
	
	@Autowired
	private PurchaseRequestLineItemRepository purchaseRequestLineItemRepo;
	
	@Autowired
	private PurchaseRequestRepository purchaseRequestRepo;
	
	// http://localhost:8080/purchase-requests-line-items/
	@GetMapping("/")
	public @ResponseBody JsonResponse getAll() {
		JsonResponse jr = null;
		try {
		 jr = JsonResponse.getInstance(purchaseRequestLineItemRepo.findAll());
		}
		catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	
			@GetMapping("/{id}")
			public @ResponseBody JsonResponse get(@PathVariable int id) {
				JsonResponse jr = null;
				try {
				
					Optional<PurchaseRequestLineItem> prli = purchaseRequestLineItemRepo.findById(id);
					if(prli.isPresent()) {
						jr = JsonResponse.getInstance(prli);
					} else {
						jr = JsonResponse.getInstance("No Purchase Request found for id: "+ id);
					}
				}
				catch (Exception e) {
					jr = JsonResponse.getInstance(e);
				}
				return jr;
			}
		@PostMapping("/purchase-requests-line-items")
		public JsonResponse add(@RequestBody PurchaseRequestLineItem prli) {
			JsonResponse jr = null;
	
			try {
				if(purchaseRequestLineItemRepo.existsById(prli.getId())) {
				
				jr = JsonResponse.getInstance(purchaseRequestLineItemRepo.save(prli));
				updateTotal(prli); 
			} else {
				jr= JsonResponse.getInstance("Purchase Request id: " +prli.getId()+" already exists and you are attempting to add");
			}
				
			}
				catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		@PutMapping("/")
		public JsonResponse update(@RequestBody PurchaseRequestLineItem prli) {
			JsonResponse jr = null;
			// NOTE: May need to enhance exception handling if more than
			// one exception type needs to be caught 
			try {
				if(purchaseRequestLineItemRepo.existsById(prli.getId())) {
					jr = JsonResponse.getInstance(purchaseRequestLineItemRepo.save(prli));
					updateTotal(prli);
				}
				else {
					jr= JsonResponse.getInstance("Purchase Request id: " +prli.getId()+" does not exist and you are"
													+ "attempting to save");
				}
			}
			catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		@DeleteMapping("/")
		public JsonResponse delete(@RequestBody PurchaseRequestLineItem prli) {
			JsonResponse jr = null;
			try {
				if(purchaseRequestLineItemRepo.existsById(prli.getId())) {
					purchaseRequestLineItemRepo.delete(prli);
					jr = JsonResponse.getInstance("Purchase Request deleted.");	
					updateTotal(prli);

				}
				else {
					jr= JsonResponse.getInstance("Purchase Request id: " +prli.getId()+" does not exist and you are"
													+ "attempting to delete it");
				}
			}
				catch (Exception e) {
					jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		public void updateTotal(PurchaseRequestLineItem prli) {
			PurchaseRequest x = prli.getPurchaseRequest();
			Iterable<PurchaseRequestLineItem> prlis =  purchaseRequestLineItemRepo.findAll();
			double total = 0;
			for (PurchaseRequestLineItem xy: prlis) {
				
				if(x.getId()==xy.getPurchaseRequest().getId()) {
					double price = xy.getProduct().getPrice();
					int quantity = xy.getQuantity();
					 total += price * quantity;
					 System.out.println(total);
					}
				x.setTotal(total);
				purchaseRequestRepo.save(x);
			}
			
		}


}


