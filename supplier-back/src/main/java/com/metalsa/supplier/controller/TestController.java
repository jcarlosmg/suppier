package com.metalsa.supplier.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metalsa.supplier.entity.NvcTblUensActivas;
import com.metalsa.supplier.repository.INvcTblUensActivasRepository;

//@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true", allowedHeaders = "*")
@RestController
//@RequestMapping("/api/suppliers/")
public class TestController {
	
	@Autowired
	private INvcTblUensActivasRepository uensActivasRepository;

	@RequestMapping("allUen")
	public List<NvcTblUensActivas> helloWorld(@RequestParam(value="name", defaultValue="World") String name) {
		
		List<NvcTblUensActivas> list = uensActivasRepository.getAllList();
		return list;
	}
	
}
