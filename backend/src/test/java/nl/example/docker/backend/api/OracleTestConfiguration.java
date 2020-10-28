package nl.example.docker.backend.api;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.OracleContainer;

import javax.sql.DataSource;

/**
 * Create database and connection dependable on the active profile
 */
@TestConfiguration
@Slf4j
public class OracleTestConfiguration {

    @Bean
    @Profile("!db-service")
    OracleContainer oracleContainer() {
        log.info("use oracleContainer bean");
        OracleContainer oracleContainer = new OracleContainer("oracleinanutshell/oracle-xe-11g:latest");
        oracleContainer.start();
        return oracleContainer;
    }

    @Bean
    @Profile("!db-service")
    DataSource dataSourceWithContainer(OracleContainer oracleContainer) {
        log.info("use datasource with oracle container");
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(oracleContainer.getJdbcUrl());
        hikariConfig.setUsername(oracleContainer.getUsername());
        hikariConfig.setPassword(oracleContainer.getPassword());

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    @Profile("db-service")
    DataSource dataSourceWithService(@Value("${test.db.host:localhost}") String host,
                                     @Value("${test.db.user:system}") String user,
                                    @Value("${test.db.pwd:oracle}") String password) {
        log.info("use datasource without oracle container");
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(String.format("jdbc:oracle:thin:@%s:1521:xe", host));
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);

        return new HikariDataSource(hikariConfig);
    }
}


