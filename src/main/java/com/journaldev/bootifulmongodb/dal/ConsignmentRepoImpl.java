package com.journaldev.bootifulmongodb.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.journaldev.bootifulmongodb.model.Consignment;

@Repository
public class ConsignmentRepoImpl implements ConsignmentRepo{

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Consignment createConsignment(Consignment consignment) {
		mongoTemplate.save(consignment);
		return consignment;
	}
	

}
