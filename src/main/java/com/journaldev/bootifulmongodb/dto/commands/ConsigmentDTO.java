package com.journaldev.bootifulmongodb.dto.commands;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="consignment1")
public class ConsigmentDTO {
	@Id
	public String boxId;
	public String parent;
	public String child;
	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public void setChild(String child) {
		this.child = child;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String location;

	public String timestamp;

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public String getBoxId() {
		return boxId;
	}

	public String getParent() {
		return parent;
	}

	public String getChild() {
		return child;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public ConsigmentDTO(String boxId, String parent, String child, String location, String timestamp) {
		this.boxId = boxId;
		this.parent = parent;
		this.child = child;
		this.location = location;
		this.timestamp = timestamp;
	}

	public ConsigmentDTO() {

	}
}
