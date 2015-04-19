package net.wazim.chestnut;

import net.wazim.chestnut.config.ApplicationConfig;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class ChestnutRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationConfig.class).showBanner(false).run();
    }

}
