package net.wazim.chestnut;


import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

@SuppressWarnings("unused")
@EnableAutoConfiguration
@ComponentScan
public class ChestnutRunner {

    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(ChestnutRunner.class, args);
    }

    public void stop() {
        applicationContext.stop();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName("localhost:5432");
        dataSource.setDatabaseName("database");
        dataSource.setUser("postgres");
        dataSource.setPassword("password");

        return new JdbcTemplate(dataSource);
    }
}