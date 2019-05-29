package com.prs.web;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.prs.business.JsonResponse;
import com.prs.business.PurchaseRequest;
import com.prs.business.User;
import com.prs.db.PurchaseRequestRepository;
import com.prs.db.UserRepository;


@RestController
@RequestMapping ("/purchase-requests")
public class PurchaseRequestController {
	@Autowired
	private PurchaseRequestRepository purchaseRequestRepo;
	@Autowired
	private UserRepository userRepo;
	
	// http://localhost:8080/purchase-requests/
	@GetMapping("/")
	public @ResponseBody JsonResponse getAll() {
		JsonResponse jr = null;
		try {
		 jr = JsonResponse.getInstance(purchaseRequestRepo.findAll());
		}
		catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	// http://localhost:8080/purchase-requests/
			@GetMapping("/{id}")
			public @ResponseBody JsonResponse get(@PathVariable int id) {
				JsonResponse jr = null;
				try {
					Optional<PurchaseRequest> pr = purchaseRequestRepo.findById(id);
					if(pr.isPresent()) {
						jr = JsonResponse.getInstance(pr);
					} else {
						jr = JsonResponse.getInstance("No Purchase Request found for id: "+ id);
					}
				}
				catch (Exception e) {
					jr = JsonResponse.getInstance(e);
				}
				return jr;
			}
//		@PostMapping("/")
//		public JsonResponse add(@RequestBody PurchaseRequest pr) {
//			JsonResponse jr = null;
//			// NOTE: May need to enhance exception handling if more than
//			// one exception type needs to be caught 
//			try {
//			 jr = JsonResponse.getInstance(purchaseRequestRepo.save(pr));
//			}
//			catch (Exception e) {
//				jr = JsonResponse.getInstance(e);
//			}
//			return jr;
//		}
		
		@PutMapping("/")
		public JsonResponse update(@RequestBody PurchaseRequest pr) {
			JsonResponse jr = null;
			// NOTE: May need to enhance exception handling if more than
			// one exception type needs to be caught 
			try {
				if(purchaseRequestRepo.existsById(pr.getId())) {
					jr = JsonResponse.getInstance(purchaseRequestRepo.save(pr));	
				}
				else {
					jr= JsonResponse.getInstance("Purchase Request id: " +pr.getId()+" does not exist and you are"
													+ "attempting to save");
				}
			}
			catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		@DeleteMapping("/")
		public JsonResponse delete(@RequestBody PurchaseRequest pr) {
			JsonResponse jr = null;
			try {
				if(purchaseRequestRepo.existsById(pr.getId())) {
					purchaseRequestRepo.delete(pr);
					jr = JsonResponse.getInstance("Purchase Request deleted.");	
				}
				else {
					jr= JsonResponse.getInstance("Purchase Request id: " +pr.getId()+" does not exist and you are"
													+ "attempting to delete it");
				}
			}
			catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		@PostMapping("/submit-new")
		public JsonResponse subNew(@RequestBody PurchaseRequest pr) {
			JsonResponse jr = null;
			try {
			if(purchaseRequestRepo.existsById(pr.getId())) {
			 pr.setStatus("New"); 
			 pr.setSubDate(LocalDateTime.now());
			 jr = JsonResponse.getInstance(purchaseRequestRepo.save(pr));
			} else {
				jr= JsonResponse.getInstance("Purchase Request id: " +pr.getId()+" does not exist.");
}
			}
			catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		@PutMapping("submit-review")
		public JsonResponse subReview(@RequestBody PurchaseRequest pr) {
			JsonResponse jr = null;
			try {
				if(purchaseRequestRepo.existsById(pr.getId())) {
				if(pr.getTotal() <= 50) {
					pr.setStatus("Approved");
				} else {
					pr.setStatus("Review");
				}
				pr.setSubDate(LocalDateTime.now());
				jr = JsonResponse.getInstance(purchaseRequestRepo.save(pr));
		
				} else {
					jr= JsonResponse.getInstance("Purchase Request id: " +pr.getId()+" does not exist.");
	}
				
			}
			catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		@GetMapping("list-review")
		public JsonResponse listReview (@RequestBody User u) {
			JsonResponse jr = null;
			try {
				if(userRepo.existsById(u.getId())) {
				Iterable<PurchaseRequest> prs = purchaseRequestRepo.findAll();
				ArrayList<PurchaseRequest> newprs = new ArrayList<>(); 
				for (PurchaseRequest x: prs) {
					if (x.getStatus().equalsIgnoreCase("review") && x.getUser() != u ) {
					newprs.add(x);
					}
					jr = JsonResponse.getInstance(newprs);
				}
				} else { 
					jr= JsonResponse.getInstance("User id: " +u.getId()+" does not exist.");
					
				}
			}
				catch (Exception e) {
					jr = JsonResponse.getInstance(e);
				}
				return jr;
			}
		
		@PutMapping("approve")
		public JsonResponse approve(@RequestBody PurchaseRequest pr) {
			JsonResponse jr = null;
			try {
				if(userRepo.existsById(pr.getId())) {
					if (!(pr.getStatus().equalsIgnoreCase("Approved"))) {
						pr.setStatus("Approved");
						jr = JsonResponse.getInstance(purchaseRequestRepo.save(pr));
					} else {
						jr= JsonResponse.getInstance("Purchase Request: " +pr.getId()+" has already been approved.");
					}
				} else {
					jr= JsonResponse.getInstance("Purchase Request id: " +pr.getId()+" does not exist.");
				}
			} 
			catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;	
		}	
		
		@PutMapping("reject")
		public JsonResponse reject(@RequestBody PurchaseRequest pr) {
			JsonResponse jr = null;
			try {
				if(userRepo.existsById(pr.getId())) {
					if (!(pr.getStatus().equalsIgnoreCase("Rejected"))) {
						pr.setStatus("Rejected");
						jr = JsonResponse.getInstance(purchaseRequestRepo.save(pr));
					} else {
						jr= JsonResponse.getInstance("Purchase Request: " +pr.getId()+" has already been rejected.");
					}
				} else {
					jr= JsonResponse.getInstance("Purchase Request id: " +pr.getId()+" does not exist.");
				}
			} 
			catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;	
		}	
			
		
}

		

