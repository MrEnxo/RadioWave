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

import static me.mrenxo.display.Formatter.text;

public abstract class RadioItem {


    public final List<Ability> abilities;

    public final List<Stat> stats;

    public final String id;




    public Component getName(ItemStack item) {
        return text("");
    }

    public List<Component> getLore(ItemStack item) {
        ArrayList<Component> lore = new ArrayList<>();

        for (Ability ability : abilities) {
            lore.add(text("<cc:main><bold>" + ability.type.name + "</bold> <cc:lmain>" + ability.name));
            boolean dashed = false;
            for (Object line : ability.description) {
                if (!dashed) {
                    lore.add(text("<cc:gray> - <cc:white>" + line));
                    dashed = true;
                } else {
                    lore.add(text("<cc:white>" + line));
                }

            }
            lore.add(text(""));
        }

        return lore;
    }

    public NBTItem getNBT(ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.getOrCreateCompound("RadioNBT").setString("id", id);

        return nbtItem;
    }

    public Material getMaterial(ItemStack item) {
        return item.getType();
    }


    public ItemStack getItem() {


        ItemStack item = new ItemStack(Material.DIRT);
        return getItemStack(item);
    }

    public ItemStack reloadItem(ItemStack item) {

        return getItemStack(item);

    }

    private ItemStack getItemStack(ItemStack item) {


        item  = getNBT(item).getItem();
        item.setType(getMaterial(item));

        ItemMeta meta = item.getItemMeta();

        meta.displayName(getName(item));
        meta.lore(getLore(item));

        item.getItemMeta().displayName();
        item.setItemMeta(meta);


        return item;
    }

    public RadioItem(String id ,List<Stat> stats, List<Ability> abilities) {
       this.id = id;
       this.stats = stats;
       this.abilities = abilities;

    }



}
