package org.coursework.servermonitoing.dto;

import org.coursework.servermonitoing.models.ServerStatus;

import java.util.UUID;

public record ServerUpdateStatus(
        UUID serverId,
        ServerStatus status
) {
}
