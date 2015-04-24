package net.wazim.chestnut.config;

import net.wazim.chestnut.ItemDatabase;
import net.wazim.chestnut.controllers.ItemManagementController;
import net.wazim.chestnut.controllers.UserManagementController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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

    @Bean
    public UserManagementController userManagementController() {
        return new UserManagementController(itemDatabase());
    }
}
