package com.cap.anurag.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cap.anurag.entities.DiagnosticCentre;
import com.cap.anurag.entities.Tests;
import com.cap.anurag.exception.RecordFoundException;
import com.cap.anurag.service.AdminService;

@RestController
@RequestMapping("/Test")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
	@Autowired
	AdminService service;
	private Random rand = new Random();

	@PostMapping("/create")
	public ResponseEntity<Boolean> create(@RequestBody Tests test) {
		System.out.println(test);
		test.setTestId(Integer.toString(rand.nextInt(1000)));

		Tests test1 = null;

		DiagnosticCentre a = service.findByCentreName(test.getCentre().getCentreName());
		if (a != null) {
			Optional<Tests> testEntity = service.findBycentreNameAndTestName(a.getCentreName(), test.getTestName());
			if (testEntity.isPresent()) {
				throw new RecordFoundException("TestName found");
			}
			test1 = new Tests(test.getTestName(), a);
		} else {
			DiagnosticCentre centre = new DiagnosticCentre(test.getCentre().getCentreName());
			service.save(centre);
			test1 = new Tests(test.getTestName(), centre);

		}

		service.addTest(test1);
		return new ResponseEntity<>(true, HttpStatus.OK);

	}

	@GetMapping("/findCentre")
	public ResponseEntity<List<DiagnosticCentre>> getCentres() {
		List<DiagnosticCentre> list = service.getCentres();
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping("/find")
	public ResponseEntity<List<Tests>> getTests() {
		List<Tests> list = service.getTests();
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);

	}

	

	@ExceptionHandler(RecordFoundException.class)
	public ResponseEntity<Boolean> userNotFound(RecordFoundException e) {
		return new ResponseEntity<>(false, HttpStatus.OK);
	}

}
