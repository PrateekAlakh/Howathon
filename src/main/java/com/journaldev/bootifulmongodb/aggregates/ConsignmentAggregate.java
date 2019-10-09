package com.journaldev.bootifulmongodb.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import com.journaldev.bootifulmongodb.commands.CreateConsigmentCommand;
import com.journaldev.bootifulmongodb.dal.ConsignmentRepo;
import com.journaldev.bootifulmongodb.dto.commands.ConsigmentDTO;
import com.journaldev.bootifulmongodb.events.ConsignmentCreatedEvent;
import com.journaldev.bootifulmongodb.model.Consignment;

@Aggregate
public class ConsignmentAggregate {

    @AggregateIdentifier
    private String id;
    private String boxId;
    
    public  String parent;
    public  String child;
    public  String timestamp;

    public ConsignmentAggregate() {
    }

    
    
    @CommandHandler
    public ConsignmentAggregate(CreateConsigmentCommand consigmentCommand ){
        AggregateLifecycle.apply(new ConsignmentCreatedEvent(consigmentCommand.id,consigmentCommand.boxId, consigmentCommand.parent,consigmentCommand.child,consigmentCommand.timestamp));
    }

    @EventSourcingHandler
    protected void on(ConsignmentCreatedEvent consignmentCreatedEvent){
        this.id = consignmentCreatedEvent.id;
        this.parent = consignmentCreatedEvent.parent;
        this.child = consignmentCreatedEvent.child;
        this.timestamp = consignmentCreatedEvent.timestamp;
        this.boxId = consignmentCreatedEvent.boxId;
        System.out.println("ABC");
        
       // AggregateLifecycle.apply(new ConsignmentCreatedEvent(this.id,this.boxId, this.parent,this.child,this.timestamp));
    }

}
