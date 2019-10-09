package com.journaldev.bootifulmongodb.services.commands;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.journaldev.bootifulmongodb.commands.CreateConsigmentCommand;
import com.journaldev.bootifulmongodb.dto.commands.ConsigmentDTO;

@Service
public class ConsignmentCommandServiceImpl implements ConsignmentCommandService {

    private final CommandGateway commandGateway;

    public ConsignmentCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }


	@Override
	public CompletableFuture<String> createConsignment(ConsigmentDTO consigmentDTO) {
	       return commandGateway.send(new CreateConsigmentCommand(UUID.randomUUID().toString(), consigmentDTO.getBoxId(),consigmentDTO.getParent(),consigmentDTO.getChild(),consigmentDTO.getTimestamp()));
	       	}
}
