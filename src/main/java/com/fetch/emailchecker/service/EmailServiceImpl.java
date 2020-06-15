package com.fetch.emailchecker.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements EmailService {

	private final String AT_SYMBOL = "@";

	@Override
	public long countEmails(List<String> emails) {
		Set<String> emailSet = emails.stream().filter(s -> isEmailValid(s)).map(s -> parseEmail(s)).collect(Collectors.toSet());
		return emailSet.size();
	}

	private boolean isEmailValid(String email) {
		return EmailValidator.getInstance().isValid(email);
	}

	private String parseEmail(String email) {
		String[] splitEmail = email.trim().split(AT_SYMBOL);
		String username = splitEmail[0];
		String domain = splitEmail[1];
		String parsedUsername = username
				.replaceAll("\\.", "")
				.replaceAll("\\+.*","");
		return parsedUsername+ AT_SYMBOL +domain;
	}
}
