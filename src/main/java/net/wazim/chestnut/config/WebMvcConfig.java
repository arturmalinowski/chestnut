package net.wazim.chestnut.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("unused")
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Controller
    static class Routes {

        @RequestMapping({
                "/",
                "/{id:\\w+}",
        })
        public String index() {
            return "forward:/chestnut/index.html";
        }
    }

}
