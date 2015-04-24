package net.wazim.chestnut.config;

import net.wazim.chestnut.ItemDatabase;
import net.wazim.chestnut.controllers.ItemManagementController;
import net.wazim.chestnut.controllers.UserManagementController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
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

    @Bean
    public UserManagementController userManagementController() {
        return new UserManagementController(itemDatabase());
    }
}
