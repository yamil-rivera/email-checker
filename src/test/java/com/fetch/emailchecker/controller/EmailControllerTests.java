package com.fetch.emailchecker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetch.emailchecker.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
public class EmailControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private EmailService emailService;

	@Test
	public void emailControllerShouldReturn200ForAValidRequest() throws Exception {

		List<String> emails = Arrays.asList("test@test.com");

		MvcResult result = mockMvc.perform(post("/v1/emails")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(emails)))
				.andReturn();

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertEquals(0, Long.valueOf(result.getResponse().getContentAsString()));
	}


	@Test
	public void emailControllerShouldReturn400ForAnInvalidRequestPayload() throws Exception {

		String email = "test@test.com";

		MvcResult result = mockMvc.perform(post("/v1/emails")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(email)))
				.andReturn();

		assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());

	}

	@Test
	public void emailControllerShouldReturn405ForAnInvalidRequestMethod() throws Exception {

		List<String> emails = Arrays.asList("test@test.com");

		MvcResult result = mockMvc.perform(put("/v1/emails")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(emails)))
				.andReturn();

		assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), result.getResponse().getStatus());

	}

	@Test
	public void emailControllerShouldReturn404ForAnInvalidRequestUrl() throws Exception {

		List<String> emails = Arrays.asList("test@test.com");

		MvcResult result = mockMvc.perform(put("/v/emails")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(emails)))
				.andReturn();

		assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());

	}

}
