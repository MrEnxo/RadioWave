package me.mrenxo.item.listener;

import de.tr7zw.nbtapi.NBTItem;
import me.mrenxo.item.Ability;
import me.mrenxo.item.AbilityType;
import me.mrenxo.item.RadioItem;
import me.mrenxo.item.RadioItemManager;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class AbilityRightClickListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) return;

        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        if (item.getType().isAir()) return;

        NBTItem nbtItem = new NBTItem(event.getPlayer().getInventory().getItemInMainHand());
        String id = nbtItem.getCompound("RadioNBT").getString("id");

        if (id == null) return;

        RadioItem radioItem = RadioItemManager.getRadioItem(id);


        if (event.hasBlock()) {
            for (Ability ability : radioItem.abilities ) {
                if (ability.type.equals(AbilityType.rightClickOnBlock)) {
                    ability.activateAbility(event);
                }
            }
        }
        for (Ability ability : radioItem.abilities ) {
            if (ability.type.equals(AbilityType.rightClick)) {
                ability.activateAbility(event);
            }
        }


    }
}
