package com.journaldev.bootifulmongodb.dal;

import java.util.List;

import com.journaldev.bootifulmongodb.dto.commands.ConsigmentDTO;
import com.journaldev.bootifulmongodb.dto.commands.TrackingDTO;


public interface ConsignmentRepo {
	
	public ConsigmentDTO createConsignment(ConsigmentDTO consignment);
	public void linkConsignment(ConsigmentDTO consigmentDTO);
	public void deLinkConsignment(ConsigmentDTO consignment);
	public List<TrackingDTO> getHistoryForBoxes(String boxId);
	public List<String> getCurrentLocations();
	public List<String> getLocationSpecificTime(String time);

}
