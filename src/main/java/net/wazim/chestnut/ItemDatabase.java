package net.wazim.chestnut;

import net.wazim.chestnut.domain.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDatabase {
    private List<Item> items = new ArrayList<Item>();

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

}
