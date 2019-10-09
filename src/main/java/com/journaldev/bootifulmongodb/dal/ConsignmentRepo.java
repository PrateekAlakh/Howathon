package com.journaldev.bootifulmongodb.dal;

import org.springframework.stereotype.Repository;

import com.journaldev.bootifulmongodb.model.Consignment;


public interface ConsignmentRepo {
	
	public Consignment createConsignment(Consignment consignment);

}
