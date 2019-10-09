package com.journaldev.bootifulmongodb.dal;

import org.springframework.stereotype.Repository;

import com.journaldev.bootifulmongodb.dto.commands.ConsigmentDTO;
import com.journaldev.bootifulmongodb.model.Consignment;


public interface ConsignmentRepo {
	
	public ConsigmentDTO createConsignment(ConsigmentDTO consignment);

}
