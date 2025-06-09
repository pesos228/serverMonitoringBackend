package org.coursework.servermonitoing.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Server {
    private final UUID id;
    private String name;
    private IpAddress ipAddress;
    private ServerStatus status;
    private final int cpuCores;
    private Memory memory;
    private Storage storage;
    private OsType osType;
    private final LocalDateTime creationDate;

    public Server(String name, IpAddress ipAddress, int cpuCores, Memory memory, Storage storage, OsType osType) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.ipAddress = ipAddress;
        this.status = ServerStatus.RUNNING;
        this.cpuCores = cpuCores;
        this.memory = memory;
        this.storage = storage;
        this.osType = osType;
        this.creationDate = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IpAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public ServerStatus getStatus() {
        return status;
    }

    public void setStatus(ServerStatus status) {
        this.status = status;
    }

    public int getCpuCores() {
        return cpuCores;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public OsType getOsType() {
        return osType;
    }

    public void setOsType(OsType osType) {
        this.osType = osType;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
