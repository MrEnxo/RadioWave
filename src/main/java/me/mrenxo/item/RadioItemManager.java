package me.mrenxo.item;

import org.bukkit.Bukkit;
import org.reflections.Reflections;

import java.util.LinkedHashMap;
import java.util.Set;

public class RadioItemManager {

    public static LinkedHashMap<String, RadioItem> itemList = new LinkedHashMap<>();

    static {
        Set<Class<? extends RadioItem>> classList = new Reflections("me.mrenxo.item.items").getSubTypesOf(RadioItem.class);
        for(Class<? extends RadioItem> clazz : classList) {
            try {
                RadioItem item = clazz.getConstructor().newInstance();
                itemList.put(item.id, item);
            } catch(Exception exception) {}
        }

    }

    public static RadioItem getRadioItem(String name) {
        return itemList.getOrDefault(name, null);
    }
}
