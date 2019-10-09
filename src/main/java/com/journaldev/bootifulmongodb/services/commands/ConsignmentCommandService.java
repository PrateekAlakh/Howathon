package com.journaldev.bootifulmongodb.services.commands;

import java.util.concurrent.CompletableFuture;

import com.journaldev.bootifulmongodb.dto.commands.ConsigmentDTO;

public interface ConsignmentCommandService {

    public CompletableFuture<String> createConsignment(ConsigmentDTO consigmentDTO);
 }
