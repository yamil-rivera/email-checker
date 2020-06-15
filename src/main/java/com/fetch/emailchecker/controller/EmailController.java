package com.fetch.emailchecker.controller;

import com.fetch.emailchecker.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/v1/emails")
	public ResponseEntity<Long> countEmails(@RequestBody List<String> emails) {
		return ResponseEntity.ok(emailService.countEmails(emails));
	}
}
