package com.docker.k8s.dockerk8sexample.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.docker.k8s.dockerk8sexample.util.APIInfo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * Controller class for the API Info. It will print all information regarding
 * the application.
 * 
 * @author TechMahindra
 */
@CrossOrigin(origins = "*")
@RestController
public class APIInfoController {

	@ApiOperation(value = "Get Information about API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Response Service"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = { "/getAPIInfo" }, method = { RequestMethod.GET }, produces = { "text/plain" })
	public String getAPIInfo() {
		APIInfo apiInfo = new APIInfo();
		return apiInfo.fetchAPIInfo();
	}

}