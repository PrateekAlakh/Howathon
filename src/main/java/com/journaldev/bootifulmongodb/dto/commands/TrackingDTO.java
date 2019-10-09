package com.journaldev.bootifulmongodb.dto.commands;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="trackingDTO")
public class TrackingDTO {


    String boxId;
    String timestamp;
    String message;
    String location;

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
