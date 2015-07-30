package net.wazim.chestnut;

import net.wazim.chestnut.config.ApplicationConfig;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class ChestnutRunner {

    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        ChestnutRunner chestnutRunner = new ChestnutRunner();
        chestnutRunner.start();
    }

    public void start() {
        applicationContext = new SpringApplicationBuilder(ApplicationConfig.class).showBanner(false).run();
    }

    public void stop() {
        applicationContext.stop();
    }

}