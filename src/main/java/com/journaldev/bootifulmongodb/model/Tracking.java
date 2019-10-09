package com.journaldev.bootifulmongodb.model;

public class Tracking {

	private String boxId;
	private String location;
	private String timestamp;
	private String status;
	private String operation;
	private String related;
	public String getBoxId() {
		return boxId;
	}
	public Tracking(String boxId, String location, String timestamp, String status, String operation, String related) {
		super();
		this.boxId = boxId;
		this.location = location;
		this.timestamp = timestamp;
		this.status = status;
		this.operation = operation;
		this.related = related;
	}
	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getRelated() {
		return related;
	}
	public void setRelated(String related) {
		this.related = related;
	}
	
	
}
