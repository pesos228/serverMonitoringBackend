package org.coursework.servermonitoing.repository;

import org.coursework.servermonitoing.dto.ServerDtoInput;
import org.coursework.servermonitoing.models.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ServerRepository {
    private final static Map<UUID, Server> storage = new HashMap<>();
    private final static Set<String> usedIpAddresses = new HashSet<>();

    public UUID save(ServerDtoInput server) {

        if (usedIpAddresses.contains(server.ipAddress())) {
            throw new IllegalArgumentException("IP address " + server.ipAddress() + " is already in use.");
        }

        var newStorage = new Storage(server.totalStorageGb());
        var newMemory = new Memory(server.totalMemoryGb());
        var ipAddress = new IpAddress(server.ipAddress());
        var osType = OsType.valueOf(server.osType().toUpperCase().replace(" ", "_"));

        var newServer = new Server(server.name(), ipAddress, server.cpuCores(), newMemory, newStorage, osType);
        storage.put(newServer.getId(), newServer);
        usedIpAddresses.add(newServer.getIpAddress().getValue());
        return newServer.getId();
    }

    public Server findById(UUID id) {
        var server = storage.get(id);
        if (server == null) {
            throw new IllegalArgumentException("Server with id " + id + " not found");
        }
        return server;
    }

    public List<Server> findAll() {
        return new ArrayList<>(storage.values());
    }
}
