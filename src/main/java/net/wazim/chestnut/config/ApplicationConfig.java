package net.wazim.chestnut.config;

import net.wazim.chestnut.controllers.ItemManagementController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@SuppressWarnings("unused")
public class ApplicationConfig {

    @Bean
    public JdbcTemplate jdbcTemplate() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource dataSource = (DataSource)applicationContext.getBean("dataSource");
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public ItemManagementController itemAdderController() {
        return new ItemManagementController(jdbcTemplate());
    }

}
