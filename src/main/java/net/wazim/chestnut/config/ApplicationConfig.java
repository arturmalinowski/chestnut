package net.wazim.chestnut.config;

import net.wazim.chestnut.ItemDatabase;
import net.wazim.chestnut.controllers.ItemManagementController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@SuppressWarnings("unused")
public class ApplicationConfig {

    @Bean
    public ItemDatabase itemDatabase() {
        return new ItemDatabase();
    }

    @Bean
    public ItemManagementController itemAdderController() {
        return new ItemManagementController(itemDatabase());
    }

}
