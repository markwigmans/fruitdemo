package nl.example.docker.backend.api;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.OracleContainer;

import javax.sql.DataSource;

@TestConfiguration
public class OracleTestConfiguration {

    @Bean
    OracleContainer oracleContainer() {
        OracleContainer oracleContainer = new OracleContainer("oracleinanutshell/oracle-xe-11g:latest");
        //OracleContainer oracleContainer = new OracleContainer("quay.io/maksymbilenko/oracle-12c:latest");
        oracleContainer.start();
        return oracleContainer;
    }

    @Bean
    DataSource dataSource(OracleContainer oracleContainer) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(oracleContainer.getJdbcUrl());
        hikariConfig.setUsername(oracleContainer.getUsername());
        hikariConfig.setPassword(oracleContainer.getPassword());

        return new HikariDataSource(hikariConfig);
    }
}


