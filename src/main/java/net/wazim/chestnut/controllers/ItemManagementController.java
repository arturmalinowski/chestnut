package net.wazim.chestnut.controllers;

import net.wazim.chestnut.ItemDatabase;
import net.wazim.chestnut.domain.Item;
import net.wazim.chestnut.domain.ItemUserRequest;
import net.wazim.chestnut.domain.User;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

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
    public HttpStatus addNewItem(@RequestBody ItemUserRequest request) {
        String requestUser = request.getUserId();
        Optional<User> user = itemDatabase.getUserById(requestUser);
        if (user.isPresent()) {
            String requestId = request.getImdbId();

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

                itemDatabase.addItem(user.get(), imdbMatchedItem);
                return HttpStatus.OK;
            }
        }
        return HttpStatus.NOT_FOUND;
    }

    @RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
    public List<Item> getItems(@PathVariable String userId) {
        Optional<User> user = itemDatabase.getUserById(userId);
        if (user.isPresent()) {
            return itemDatabase.getItemsForUser(user.get());
        }
        throw new IllegalArgumentException("No user found");
    }

}
