package com.prs.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.prs.business.JsonResponse;
import com.prs.business.Vendor;
import com.prs.db.VendorRepository;

@RestController
@RequestMapping("/vendors")
public class VendorController {
	
	@Autowired
	private VendorRepository vendorRepo;
	
	// http://localhost:8080/vendors/
	@GetMapping("/")
	public @ResponseBody JsonResponse getAll() {
		JsonResponse jr = null;
		try {
		 jr = JsonResponse.getInstance(vendorRepo.findAll());
		}
		catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	// http://localhost:8080/vendors/
			@GetMapping("/{id}")
			public @ResponseBody JsonResponse get(@PathVariable int id) {
				JsonResponse jr = null;
				try {
					Optional<Vendor> v = vendorRepo.findById(id);
					if(v.isPresent()) {
						jr = JsonResponse.getInstance(v);
					} else {
						jr = JsonResponse.getInstance("No Vendor found for id: "+ id);
					}
				}
				catch (Exception e) {
					jr = JsonResponse.getInstance(e);
				}
				return jr;
			}
		@PostMapping("/")
		public JsonResponse add(@RequestBody Vendor v) {
			JsonResponse jr = null;
			// NOTE: May need to enhance exception handling if more than
			// one exception type needs to be caught 
			try {
			 jr = JsonResponse.getInstance(vendorRepo.save(v));
			}
			catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		@PutMapping("/")
		public JsonResponse update(@RequestBody Vendor v) {
			JsonResponse jr = null;
			// NOTE: May need to enhance exception handling if more than
			// one exception type needs to be caught 
			try {
				if(vendorRepo.existsById(v.getId())) {
					jr = JsonResponse.getInstance(vendorRepo.save(v));	
				}
				else {
					jr= JsonResponse.getInstance("Vendor id: " +v.getId()+" does not exist and you are"
													+ "attempting to save");
				}
			}
			catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		@DeleteMapping("/")
		public JsonResponse delete(@RequestBody Vendor v) {
			JsonResponse jr = null;
			try {
				if(vendorRepo.existsById(v.getId())) {
					vendorRepo.delete(v);
					jr = JsonResponse.getInstance("Vendor deleted.");	
				}
				else {
					jr= JsonResponse.getInstance("Vendor id: " +v.getId()+" does not exist and you are"
													+ "attempting to delete it");
				}
			}
			catch (Exception e) {
				jr = JsonResponse.getInstance(e);
			}
			return jr;
		}
		
		
		
		
		
	
	


}
