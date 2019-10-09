package com.journaldev.bootifulmongodb.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.journaldev.bootifulmongodb.dto.commands.ConsigmentDTO;
import com.journaldev.bootifulmongodb.model.Consignment;

@Repository
public class ConsignmentRepoImpl implements ConsignmentRepo{

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public ConsigmentDTO createConsignment(ConsigmentDTO consignment) {
		mongoTemplate.save(consignment);
		return consignment;
	}
	

}
