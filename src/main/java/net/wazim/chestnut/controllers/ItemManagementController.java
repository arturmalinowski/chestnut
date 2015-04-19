package net.wazim.chestnut.controllers;

import net.wazim.chestnut.ItemDatabase;
import net.wazim.chestnut.domain.Item;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping(value = "items")
public class ItemManagementController {

    private final ItemDatabase itemDatabase;
    private final RestTemplate restTemplate;

    public ItemManagementController(ItemDatabase itemDatabase) {
        this.itemDatabase = itemDatabase;
        this.restTemplate = new RestTemplate();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus addNewItem(@RequestBody Item item) {
        String requestId = item.getImdbId();

        String response = restTemplate.getForObject(String.format("http://www.omdbapi.com/?i=%s&plot=short&r=json", requestId), String.class);
        JSONObject jsonObject = new JSONObject(response);
        String omdbResponse = jsonObject.getString("Response");

        if ("True".equals(omdbResponse)) {
            Item imdbMatchedItem = new Item(
                    jsonObject.getString("imdbID"),
                    jsonObject.getString("Title"),
                    jsonObject.getString("Poster"),
                    jsonObject.getString("Type"),
                    jsonObject.getString("Plot")
            );

            itemDatabase.addItem(imdbMatchedItem);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Item> getItems() {
        return itemDatabase.getItems();
    }

}
