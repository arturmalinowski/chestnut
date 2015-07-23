package net.wazim.chestnut;

import net.wazim.chestnut.domain.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDatabase {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItemsForUser() {
        return items;
    }

}
