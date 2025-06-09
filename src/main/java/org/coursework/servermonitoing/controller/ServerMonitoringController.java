package org.coursework.servermonitoing.controller;

import org.coursework.servermonitoing.dto.ServerDtoInput;
import org.coursework.servermonitoing.dto.ServerUpdateStatus;
import org.coursework.servermonitoing.models.OsType;
import org.coursework.servermonitoing.models.Server;
import org.coursework.servermonitoing.repository.ServerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/servers")
@CrossOrigin(origins = "*")
public class ServerMonitoringController {

    private static final Logger logger = LoggerFactory.getLogger(ServerMonitoringController.class);
    private final ServerRepository serverRepository;

    @Autowired
    public ServerMonitoringController(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @GetMapping
    public List<Server> getServers() {
        logger.info("Request received to fetch all servers.");
        List<Server> servers = serverRepository.findAll();
        logger.info("Returning {} servers.", servers.size());
        return servers;
    }

    @PostMapping
    public Server newServer(@RequestBody ServerDtoInput serverDtoInput) {
        logger.info("Request received to create a new server with name: {}", serverDtoInput.name());
        UUID newServerId = serverRepository.save(serverDtoInput);
        Server createdServer = serverRepository.findById(newServerId);
        logger.info("Successfully created server with ID: {}", newServerId);
        return createdServer;
    }

    @PostMapping("/update-status")
    public Server updateStatus(@RequestBody ServerUpdateStatus serverUpdateStatus) {
        logger.info("Request received to update server status for ID: {} to {}",
                serverUpdateStatus.serverId(), serverUpdateStatus.status());

        Server server = serverRepository.findById(serverUpdateStatus.serverId());
        server.setStatus(serverUpdateStatus.status());

        logger.info("Status for server ID: {} updated successfully.", serverUpdateStatus.serverId());
        return server;
    }

    @GetMapping("/{id}")
    public Server getServer(@PathVariable String id) {
        logger.info("Request received to fetch server with ID: {}", id);
        UUID uuid = UUID.fromString(id);
        Server server = serverRepository.findById(uuid);
        logger.info("Returning server with name: {}", server.getName());
        return server;
    }

    @GetMapping("/os-list")
    public List<OsType> getOsTypes() {
        logger.debug("Request received to fetch OS types list.");
        List<OsType> osTypes = List.of(OsType.values());
        logger.debug("Returning {} OS types.", osTypes.size());
        return osTypes;
    }
}
