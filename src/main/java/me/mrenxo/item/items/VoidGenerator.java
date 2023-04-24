package me.mrenxo.item.items;

import de.tr7zw.nbtapi.NBTItem;
import me.mrenxo.data.LootTable;
import me.mrenxo.item.Ability;
import me.mrenxo.item.AbilityType;
import me.mrenxo.item.RadioItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static me.mrenxo.display.Formatter.numberFormat;
import static me.mrenxo.display.Formatter.text;

public class VoidGenerator extends RadioItem {

    private static HashMap<Integer, LootTable<Material>> lootPools = new HashMap<>() {{
        put(1,
                new LootTable<>(
                        new LootTable.Entry<>(Material.DIRT, 50),
                        new LootTable.Entry<>(Material.DRIED_KELP, 7),
                        new LootTable.Entry<>(Material.OAK_PLANKS, 36),
                        new LootTable.Entry<>(Material.COBBLESTONE, 7)
                )
        );
    }};
    private static HashMap<Integer, Material> itemTypes = new HashMap<>() {{
        put(1, Material.MUSIC_DISC_CHIRP);
    }};

    public VoidGenerator() {
        super("Void Generator", List.of(), List.of(
                new Ability<>(
                        "Open GUI",
                        List.of("open the menu for this generator"),
                        AbilityType.leftClick,
                        true,
                        VoidGenerator::leftClick
                ),
                new Ability<>(
                        "Generate",
                        List.of("generates a random item from this", "generator's loot pool"),
                        AbilityType.rightClick,
                        true,
                        VoidGenerator::rightClick
                )


        ));
    }

    private static void leftClick(PlayerInteractEvent e) {

    }

    private static void rightClick(PlayerInteractEvent e) {
        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
        NBTItem nbtItem = new NBTItem(item);
        Integer lootPool = nbtItem.getCompound("RadioNBT").getInteger("generator");
        e.getPlayer().getInventory().addItem(new ItemStack(lootPools.get(lootPool).roll()));
        nbtItem.getCompound("RadioNBT").setLong("clicks", nbtItem.getCompound("RadioNBT").getLong("clicks") + 1);
        e.getPlayer().getInventory().setItemInMainHand(nbtItem.getItem());

    }

    @Override
    public Material getMaterial(ItemStack item) {
        Integer tier = new NBTItem(item).getCompound("RadioNBT").getInteger("generator");
        return itemTypes.get(tier);
    }

    @Override
    public Component getName(ItemStack item) {
        Integer tier = new NBTItem(item).getCompound("RadioNBT").getInteger("generator");
        return text("<cc:main><b>\u00BB</b> <cc:lmain>Tier " + tier + " Generator <cc:main><b>\u00AB</b>");
    }

    @Override
    public List<Component> getLore(ItemStack item) {
        ArrayList<Component> lore = new ArrayList<>();

        Long clicks = new NBTItem(item).getCompound("RadioNBT").getLong("clicks");
        lore.add(text("<dark_gray>Clicks: " + ((clicks > 1000) ? numberFormat(clicks) : clicks)));
        lore.add(text(""));

        lore.addAll(super.getLore(item));

        return lore;
    }

    @Override
    public NBTItem getNBT(ItemStack item) {
        NBTItem nbtItem = super.getNBT(item);
        if (!nbtItem.getOrCreateCompound("RadioNBT").hasTag("generator")) {
            nbtItem.getCompound("RadioNBT").setInteger("generator", 1);
        }
        if (!nbtItem.getOrCreateCompound("RadioNBT").hasTag("clicks")) {
            nbtItem.getCompound("RadioNBT").setInteger("clicks", 0);
        }
        nbtItem.setInteger("HideFlags", 32);
        return nbtItem;
    }
}
