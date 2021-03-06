/**
 * 
 */
package com.SS.Administrator.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SS.Administrator.Entity.Borrower;

/**
 * @author acorb
 *
 */
@SpringBootTest
class BorrowerServiceTest {
	@Autowired
	private BorrowerService service;

	@Test
	void testAddFailCases() {
//		try {
//			Borrower borrower = new Borrower();
//			borrower.setAddress(null);
//			service.addBorrower(borrower);
//			fail();
//		}catch(IllegalArgumentException e) {}
//		try {
//			Borrower borrower = new Borrower();
//			borrower.setAddress(null);
//			borrower.setName("fake name");
//			borrower.setPhone("fake phone");
//			borrower.setCardNo(null);
//			service.addBorrower(borrower);
//			fail();
//		}catch(IllegalArgumentException e) {}
//		try {
//			Borrower borrower = new Borrower();
//			borrower.setAddress("test addresss");
//			borrower.setName(null);
//			borrower.setPhone("fake phone");
//			borrower.setCardNo(null);
//			service.addBorrower(borrower);
//			fail();
//		}catch(IllegalArgumentException e) {}
//		try {
//			Borrower borrower = new Borrower();
//			borrower.setAddress("test addresss");
//			borrower.setName("fake name");
//			borrower.setPhone(null);
//			borrower.setCardNo(null);
//			service.addBorrower(borrower);
//			fail();
//		}catch(IllegalArgumentException e) {}
//		try {
//			Borrower borrower = new Borrower();
//			borrower.setAddress(null);
//			borrower.setName("fake name");
//			borrower.setPhone(null);
//			borrower.setCardNo(null);
//			service.addBorrower(borrower);
//			fail();
//		}catch(IllegalArgumentException e) {}
//		try {
//			Borrower borrower = new Borrower();
//			borrower.setAddress("test addresss");
//			borrower.setName(null);
//			borrower.setPhone(null);
//			borrower.setCardNo(null);
//			service.addBorrower(borrower);
//			fail();
//		}catch(IllegalArgumentException e) {}
		try {
			Borrower borrower = new Borrower();
			borrower.setAddress("test addresss");
			borrower.setName("fake name");
			borrower.setPhone("fake phone");
			borrower.setCardNo(1000);
			service.addBorrower(borrower);
			fail();
		}catch(IllegalArgumentException e) {}
	}
	@Test
	void testUpdateFail(){
		try {
			Borrower borrower = new Borrower();
			borrower.setCardNo(-1);
			service.updateBorrower(borrower);
			fail();
			}catch(NoSuchElementException e) {}
		try {
			service.findBorrowerById(-1);
			fail();
		}catch(NoSuchElementException e) {}
	}

	@Test
	void testAddThenDelete() {
		Borrower borrower = new Borrower();
		borrower.setAddress("fake address");
		borrower.setCardNo(null);
		borrower.setName("alias");
		borrower.setPhone("notevennumber");
		service.addBorrower(borrower);
		assertNotNull(borrower.getCardNo());
		
		//testfindbyID
		Borrower foundById = service.findBorrowerById(borrower.getCardNo());
		assertEquals(borrower, foundById);
		
		//testUpdateSuccesfull
		borrower.setAddress("can i be faker");
		service.updateBorrower(borrower);
		
		//delete test subject
		service.deleteBorrower(borrower.getCardNo());
	}
	@Test
	void testReadAll() {
		assertNotEquals(0, service.readAllBorrowers());
	}
}
