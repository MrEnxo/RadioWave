package me.mrenxo.item;

import de.tr7zw.nbtapi.NBTItem;
import me.mrenxo.display.Formatter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class RadioItem {

    public final String name;
    public final List<String> description;

    public final Material material;

    public final List<Ability> abilities;

    public final List<Stat> stats;


    public List<Component> processLore() {
        ArrayList<Component> finalLore = new ArrayList<>();

        for (String line : description) {
            finalLore.add(Formatter.text(line));
        }

        return finalLore;
    }

    public ItemStack getItem() {


        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(Formatter.text(name));
        meta.lore(processLore());

        item.getItemMeta().displayName();
        item.setItemMeta(meta);

        NBTItem nbtItem = new NBTItem(item);
        nbtItem.getOrCreateCompound("RadioNBT").setString("id", name);
        item = nbtItem.getItem();

        return item;
    }

    public RadioItem(String name, List<String> description, Material material,List<Stat> stats, List<Ability> abilities) {
        this.name = name;
        this.description = description;
        this.material = material;
        this.abilities = abilities;
        this.stats = stats;

    }



}
