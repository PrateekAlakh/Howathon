package com.journaldev.bootifulmongodb.dal;

import com.journaldev.bootifulmongodb.dto.commands.TrackingDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.stereotype.Repository;
import com.journaldev.bootifulmongodb.dto.commands.ConsigmentDTO;

@Repository
public class ConsignmentRepoImpl implements ConsignmentRepo{

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public ConsigmentDTO createConsignment(ConsigmentDTO consignment) {

		TrackingDTO trackingDTO = new TrackingDTO();
		ConsigmentDTO consigmentDTO = mongoTemplate.findById(consignment.getBoxId(), ConsigmentDTO.class);
		if (consigmentDTO== null){
			mongoTemplate.save(consignment);

			logTrackingMessage(consignment,"CREATE");

		}else{
			String location = consignment.getLocation();
			consigmentDTO.setLocation(location);
			if(consigmentDTO.getChild() != null)
				updateChilds(consigmentDTO.getChild(), location);
			mongoTemplate.save(consigmentDTO);
			logTrackingMessage(consigmentDTO, "MOVE");

		}
		mongoTemplate.save(consignment);
		return consignment;
	}


	public void updateChilds(String child, String location){
		String[] childArray = child.split(",");
		for (String child1: childArray){
			ConsigmentDTO consigmentDTO = mongoTemplate.findById(child1, ConsigmentDTO.class);
			consigmentDTO.setLocation(location);
			mongoTemplate.save(consigmentDTO);
			logTrackingMessage(consigmentDTO, "MOVE");
			if(consigmentDTO.getChild() != null && !consigmentDTO.getChild().isEmpty()){
				updateChilds(consigmentDTO.getChild(), location);
			}
		}
	}

	public void logTrackingMessage(ConsigmentDTO consigmentDTO, String status){
		TrackingDTO trackingDTO = new TrackingDTO();
		trackingDTO.setBoxId(consigmentDTO.getBoxId());
		trackingDTO.setTimestamp(consigmentDTO.getTimestamp());
		trackingDTO.setLocation(consigmentDTO.getLocation());
		switch (status) {
			case "CREATE":
				trackingDTO.setMessage("Box #" + trackingDTO.getBoxId() + " is at location " +
						trackingDTO.getLocation() + " at " + trackingDTO.getTimestamp());
				break;

			case "MOVE":
				trackingDTO.setMessage("Box #" + trackingDTO.getBoxId() + " is at location " +
						trackingDTO.getLocation() + " at " + trackingDTO.getTimestamp());
				break;

			case "LINK":
				trackingDTO.setMessage("Box #" + trackingDTO.getBoxId() + " has been put inside " +
						consigmentDTO.getParent() + " at " + trackingDTO.getTimestamp());
				break;


			default:
		}
		mongoTemplate.save(trackingDTO);

	}

	@Override
	public void linkConsignment(ConsigmentDTO consignment){
		ConsigmentDTO consigmentDTO = mongoTemplate.findById(consignment.getBoxId(), ConsigmentDTO.class);
		ConsigmentDTO parentDTO = mongoTemplate.findById(consignment.getParent(), ConsigmentDTO.class);
		if (consigmentDTO== null) {
			createConsignment(consignment);
		}
		if(parentDTO == null && consigmentDTO==null){
			 ConsigmentDTO parent = new ConsigmentDTO();
			 parent.setBoxId(consignment.getParent());
			 parent.setTimestamp(consignment.getTimestamp());
			 createConsignment(parent);
		}
		if(parentDTO == null && consigmentDTO!=null){
			ConsigmentDTO parent = new ConsigmentDTO();
			parent.setBoxId(consignment.getParent());
			parent.setTimestamp(consignment.getTimestamp());
			parent.setLocation(consigmentDTO.getLocation());
			createConsignment(parent);
		}
		consigmentDTO = mongoTemplate.findById(consignment.getBoxId(), ConsigmentDTO.class);
		parentDTO = mongoTemplate.findById(consignment.getParent(), ConsigmentDTO.class);

		consigmentDTO.setParent(parentDTO.getBoxId());

		mongoTemplate.save(consigmentDTO);
		if(parentDTO.getChild() != null && parentDTO.getChild().contains(consigmentDTO.getBoxId())){

		}
		else if(parentDTO.getChild() != null && !parentDTO.getChild().isEmpty()){
			parentDTO.setChild(parentDTO.getChild() + "," + consigmentDTO.getBoxId());
		}else{
			parentDTO.setChild(consigmentDTO.getBoxId());
		}

		mongoTemplate.save(parentDTO);
//		logTrackingMessage(consigmentDTO, "LINK");

		logTrackForLinking(parentDTO);

//		consigmentDTO1.setLocation(consigmentDTO.getLocation());


	}




	public void logTrackForLinking(ConsigmentDTO parentDTO){
		String[] child = parentDTO.getChild().split(",");

		for(String c: child){
			String msg = "";
			String id = "";
			ConsigmentDTO c1 = 	mongoTemplate.findById(c,ConsigmentDTO.class);
			ConsigmentDTO p = mongoTemplate.findById(c1.getParent(), ConsigmentDTO.class);
			id = c1.getBoxId();
			if(p!=null && p.getParent()!=null && !p.getParent().isEmpty()){
				ConsigmentDTO p1 = mongoTemplate.findById(p.getParent(), ConsigmentDTO.class);
				while (p1 != null){
					msg += id + " inside " + p.getBoxId() + ", ";
					id = p.getBoxId();

					p = mongoTemplate.findById(p.getParent(), ConsigmentDTO.class);
					if(p!=null && p.getParent()!=null)
						p1 = mongoTemplate.findById(p.getParent(), ConsigmentDTO.class);
					else
						p1=null;
				}





			}
			if(p!=null && (p.getParent()==null || p.getParent().isEmpty())){
				msg += id + " inside " + p.getBoxId() + "at" + parentDTO.getTimestamp() ;
				if(c1.getChild()!=null && !c1.getChild().isEmpty())
				logTrackForLinking(c1);

			}
			TrackingDTO trackingDTO = new TrackingDTO();
			trackingDTO.setBoxId(c1.getBoxId());
			trackingDTO.setTimestamp(c1.getTimestamp());
			trackingDTO.setLocation(c1.getLocation());
			trackingDTO.setMessage(msg);
			mongoTemplate.save(trackingDTO);

		}

	}

	@Override
	public void deLinkConsignment(ConsigmentDTO consignment){

		ConsigmentDTO consigmentDTO = mongoTemplate.findById(consignment.getBoxId(), ConsigmentDTO.class);
		if(consigmentDTO != null && consigmentDTO.getParent() != null){
			ConsigmentDTO parentDTO = mongoTemplate.findById(consignment.getParent(), ConsigmentDTO.class);
			consigmentDTO.setParent(null);

			String childs = parentDTO.getChild();
			if(childs.contains(","+consignment.getBoxId())) {
				childs=childs.replace("," + consignment.getBoxId(), "");
			}
			else if(childs.contains(consignment.getBoxId()+",")){
				childs=childs.replace(consignment.getBoxId()+",", "");
			}else{
				childs=childs.replace(consignment.getBoxId(), "");
			}
			parentDTO.setChild(childs);
			mongoTemplate.save(consigmentDTO);
			mongoTemplate.save(parentDTO);
			String msg =  "Box #"+consigmentDTO.getBoxId()+" has been removed from Box #" + parentDTO.getBoxId() + "at" + consignment.getTimestamp() ;

			TrackingDTO trackingDTO = new TrackingDTO();
			trackingDTO.setBoxId(consignment.getBoxId());
			trackingDTO.setTimestamp(consignment.getTimestamp());
			trackingDTO.setLocation(consignment.getLocation());
			trackingDTO.setMessage(msg);
			mongoTemplate.save(trackingDTO);
		}

	}

}
