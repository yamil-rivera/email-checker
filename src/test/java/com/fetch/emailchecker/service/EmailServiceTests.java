package com.fetch.emailchecker.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class EmailServiceTests {

	@Autowired
	private EmailService emailService;

	@Test
	public void emailServiceShouldCountEmailsCorrectly() {
		List<String> emails = new ArrayList<>(Arrays.asList("test1@test.com", "test.2@test.com", "test+3@test.com"));
		long count = emailService.countEmails(emails);
		Assertions.assertEquals(count, 3);
	}


	@Test
	public void emailServiceShouldIgnoreDuplicateEmails() {
		List<String> emails = new ArrayList<>(Arrays.asList("test1@test.com", "test1@test.com"));
		long count = emailService.countEmails(emails);
		Assertions.assertEquals(count, 1);
	}

	@Test
	public void emailServiceShouldIgnoreDuplicateGmailEmails() {
		List<String> emails = new ArrayList<>(
				Arrays.asList("test1@test.com",
							"test.1@test.com",
							"t.est.1@test.com",
							"test1+123@test.com",
							"test1+123+xyz@test.com"));
		long count = emailService.countEmails(emails);
		Assertions.assertEquals(count, 1);
	}

	@Test
	public void emailServiceShouldIgnoreInvalidEmails() {
		List<String> emails = new ArrayList<>(
				Arrays.asList("test1@test.com",
						"test2test.com",
						"test3",
						"12345",
						""));
		long count = emailService.countEmails(emails);
		Assertions.assertEquals(count, 1);
	}

	@Test
	public void emailServiceShouldHandleLotsOfEmailsCorrectly() {
		List<String> emails = new ArrayList<>();
		String emailBase = "email";
		for (long i = 0; i < 1000; i++) {
			emails.add(emailBase+i+"@test.com");
		}
		long count = emailService.countEmails(emails);
		Assertions.assertEquals(count, 1000);
	}

	@Test
	public void emailServiceShouldHandleLotsOfRepeatedEmailsCorrectly() {
		List<String> emails = new ArrayList<>();
		String emailBase = "email";
		for (long i = 0; i < 1000; i++) {
			emails.add(emailBase+i+"@test.com");
		}
		// duplicated
		for (long i = 0; i < 1000; i++) {
			emails.add(emailBase+i+"@test.com");
		}
		// '+' duplicated
		for (long i = 0; i < 1000; i++) {
			emails.add(emailBase+i+"+"+i+"@test.com");
		}
		// '.' duplicated
		for (long i = 0; i < 1000; i++) {
			emails.add(emailBase+"."+i+"@test.com");
		}
		long count = emailService.countEmails(emails);
		Assertions.assertEquals(count, 1000);
	}

	@Test
	public void emailServiceShouldHandleLotsOfInvalidEmailsCorrectly() {
		List<String> emails = new ArrayList<>();
		String emailBase = "email";
		for (long i = 0; i < 1000; i++) {
			emails.add(emailBase+i);
		}
		long count = emailService.countEmails(emails);
		Assertions.assertEquals(count, 0);
	}

}
