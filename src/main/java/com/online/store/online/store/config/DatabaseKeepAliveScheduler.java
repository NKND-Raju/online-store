package com.online.store.online.store.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseKeepAliveScheduler {

    private final DataSource dataSource;

    @PostConstruct
    public void init() {
        log.info("Database KeepAlive Scheduler initialized");
    }

    // Runs every 4 minutes (you can adjust as needed)
    @Scheduled(fixedDelay = 240000)
    public void keepConnectionAlive() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("SELECT 1");
            log.info("Successfully pinged the database to keep connection alive.");
        } catch (Exception e) {
            log.error("Failed to ping database: ", e);
        }
    }
}

