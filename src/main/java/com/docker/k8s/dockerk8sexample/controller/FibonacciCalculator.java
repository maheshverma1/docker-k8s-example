package com.docker.k8s.dockerk8sexample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * 
 * @author Mahesh Kumar
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/calculator/fibseries")

public class FibonacciCalculator {

	@ApiOperation(value = "Get Finanacci services for given number")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Fibonacci series calculation started"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = { "/{number}" }, method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<Object> initProcess(@PathVariable("number") Integer number) {

		try {

			int a = 0;
			int b = 0;
			int c = 1;
			int count = 1;
			List<Integer> fibSeries = new ArrayList<>();
			while (count <= number) {

				a = b;
				b = c;
				fibSeries.add(c);
				c = a + b;
				count++;
			}

			System.out.println(fibSeries);
			return (new ResponseEntity<Object>(fibSeries, HttpStatus.OK));
		} catch (Exception ex) {
			ex.printStackTrace();
			return (new ResponseEntity<Object>("Fibonacci calculator issue", HttpStatus.INTERNAL_SERVER_ERROR));
		}
	}

}