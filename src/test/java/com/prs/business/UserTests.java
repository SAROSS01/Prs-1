package com.prs.business;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prs.business.User;
import com.prs.db.UserRepository;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
	
	@Autowired
	private UserRepository userRepo;
	
	@Test
	public void testUserGetAll() {
		Iterable<User> users = userRepo.findAll();
		assertNotNull(users);
		
		
	}
	
	//@Before
	//public void testUserAddAndDelete() {
		//User u = new User(0, "userName","password","firstName","lastName", "phoneNumber", "email",true,true);
		// save a user
		//assertNotNull(userRepo.save(u));
		// assert the lasName is 'lastName'
		//assertEquals("lastName", u.getLastName());
		// delete the user
		//userRepo.delete(u);
		//
		//assertFalse(userRepo.findById(u.getId()).get()
	//}

}
