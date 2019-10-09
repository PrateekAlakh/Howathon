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

	public String timestamp;

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

}
