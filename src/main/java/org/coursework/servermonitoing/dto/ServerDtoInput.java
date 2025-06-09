package org.coursework.servermonitoing.dto;

public record ServerDtoInput(
        String name,
        String ipAddress,
        int cpuCores,
        float totalMemoryGb,
        float totalStorageGb,
        String osType
) {
}
