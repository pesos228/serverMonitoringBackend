package org.coursework.servermonitoing;

import org.coursework.servermonitoing.dto.ServerDtoInput;
import org.coursework.servermonitoing.models.IpAddress;
import org.coursework.servermonitoing.models.Server;
import org.coursework.servermonitoing.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class ServerMonitoringBackendApplication {

    private static final Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(ServerMonitoringBackendApplication.class, args);

    }

    @Bean
    public CommandLineRunner loadInitialData(ServerRepository repository) {
        return args -> {
                ServerDtoInput server1Dto = new ServerDtoInput(
                        "Production Web Server Alpha",
                        IpAddress.random().getValue(),
                        4,
                        16.0f,
                        250.0f,
                        "UBUNTU_22_04"
                );
                var server1Id = repository.save(server1Dto);
                ServerDtoInput server2Dto = new ServerDtoInput(
                        "Development Database Server",
                        IpAddress.random().getValue(),
                        2,
                        8.0f,
                        500.0f,
                        "CENTOS"
                );
                var server2Id = repository.save(server2Dto);
                ServerDtoInput server3Dto = new ServerDtoInput(
                        "Testing Environment",
                        IpAddress.random().getValue(),
                        8,
                        32.0f,
                        100.0f,
                        "WINDOWS"
                );
                var server3Id = repository.save(server3Dto);

                repository.findAll().forEach(this::setRandomUsedResources);
        };
    }


    private void setRandomUsedResources(Server server) {
        if (server.getMemory() != null) {
            float totalMemory = server.getMemory().getTotalCapacityGB();
            float usedMemory = totalMemory * (0.1f + ServerMonitoringBackendApplication.random.nextFloat() * 0.8f);
            server.getMemory().setUsedMemory(usedMemory);
        }

        if (server.getStorage() != null) {
            float totalStorage = server.getStorage().getTotalCapacityGB();
            float usedStorage = totalStorage * (0.05f + ServerMonitoringBackendApplication.random.nextFloat() * 0.7f);
            server.getStorage().setUsedMemoryGB(usedStorage);
        }
    }
}
