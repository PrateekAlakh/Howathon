package com.journaldev.bootifulmongodb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.journaldev.bootifulmongodb.dal.ConsignmentRepo;
import com.journaldev.bootifulmongodb.model.Consignment;

@RestController
@RequestMapping(value="/")
public class WareHouseController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ConsignmentRepo consignmentRepo;
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@RequestMapping(value="check",method=RequestMethod.GET)
	public String getResult() {
		List<Consignment> data=mongoTemplate.findAll(Consignment.class);
		data.stream().forEach(System.out::println);
		return "sagar";
	}


	@RequestMapping(value="create", method=RequestMethod.POST)
	public void create(@RequestBody Consignment consignment) {
		consignmentRepo.createConsignment(consignment);
	}
	
	
	
}
