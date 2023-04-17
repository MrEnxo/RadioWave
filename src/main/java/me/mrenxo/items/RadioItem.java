package me.mrenxo.items;

import me.mrenxo.display.Formatter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class RadioItem {

    private String name;
    private List<String> description;

    private Material material;


    public Material getMaterial() {
        return material;
    }
    public List<String> getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }

    public ItemStack getItem() {


        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(Formatter.text(name));

        item.getItemMeta().displayName();
        item.setItemMeta(meta);


        return item;
    }

    public RadioItem() {

    }



}
