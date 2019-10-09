package com.journaldev.bootifulmongodb.commands;

public class CreateConsigmentCommand extends BaseCommand<String> {

    public final String parent;
    public final String boxId;
    public final String child;
    public final String timestamp;
	
	public CreateConsigmentCommand(String id,String boxId, String parent, String child, String timestamp) {
		super(id);
		this.boxId=boxId;
		this.parent = parent;
		this.child = child;
		this.timestamp = timestamp;
	}

}
