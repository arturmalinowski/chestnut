package net.wazim.chestnut;

import net.wazim.chestnut.domain.Item;
import net.wazim.chestnut.domain.User;

import java.util.*;

import static java.util.Arrays.asList;

public class ItemDatabase {
    private Map<User, List<Item>> usersAndItems = new HashMap<>();

    public void createNewUser(String userId) {
        usersAndItems.putIfAbsent(new User(userId), asList());
    }

    public void addItem(User user, Item item) {
        List<Item> itemsForUser = usersAndItems.get(user);
        if(itemsForUser != null && itemsForUser.size() > 0) {
            List<Item> newItems = new ArrayList<>();
            newItems.addAll(itemsForUser);
            newItems.add(item);
            usersAndItems.replace(user, itemsForUser, newItems);
        } else {
            usersAndItems.put(user, asList(item));
        }
    }

    public List<Item> getItemsForUser(User user) {
        return usersAndItems.get(user);
    }

    public Optional<User> getUserById(String userId) {
        return usersAndItems.keySet().stream().filter(user -> user.getUserId().equals(userId)).findFirst();
    }

}
