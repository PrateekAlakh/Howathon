package com.journaldev.bootifulmongodb.events;

public class ConsignmentCreatedEvent extends BaseEvent<String> {

	   public final String boxId;
	   public final String parent;
	    public final String child;
	    public final String timestamp;


    public ConsignmentCreatedEvent(String id,String boxId, String parent, String child, String timestamp) {
			super(id);
			this.boxId=boxId;
			this.parent = parent;
			this.child = child;
			this.timestamp = timestamp;
		}


}
