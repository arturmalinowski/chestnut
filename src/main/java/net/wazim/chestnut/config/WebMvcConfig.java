package net.wazim.chestnut.config;

import net.wazim.chestnut.domain.ItemRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
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

        @RequestMapping(value = "/addItem", method = RequestMethod.POST)
        public @ResponseBody void addItem(@RequestBody String title) {
            RestTemplate restTemplate = new RestTemplate();
            ItemRequest item = new ItemRequest();
            item.setImdbId(title);
            restTemplate.postForEntity("http://localhost:8080/items", item, String.class);
        }
    }

}
