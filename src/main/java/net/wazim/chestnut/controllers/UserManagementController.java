package net.wazim.chestnut.controllers;

import net.wazim.chestnut.ItemDatabase;
import net.wazim.chestnut.domain.Item;
import net.wazim.chestnut.domain.ItemUserRequest;
import net.wazim.chestnut.domain.User;
import net.wazim.chestnut.domain.UserRequest;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@RestController
@RequestMapping(value = "users")
public class UserManagementController {

    private final ItemDatabase itemDatabase;

    public UserManagementController(ItemDatabase itemDatabase) {
        this.itemDatabase = itemDatabase;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus createNewUser(@RequestBody UserRequest request) {
        String requestUser = request.getUserId();
        Optional<User> user = itemDatabase.getUserById(requestUser);
        if(!user.isPresent()) {
            itemDatabase.createNewUser(requestUser);
            return HttpStatus.OK;
        }
        throw new IllegalArgumentException("Username is already in use");
    }

}
