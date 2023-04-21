package me.mrenxo.item.commands;

import me.mrenxo.item.RadioItem;
import me.mrenxo.item.RadioItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public class RadioItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("admin")) {
            return true;
        }

        Inventory inventory = Bukkit.createInventory(null, 54, "Silly items");
        int index = 0;



        for(Map.Entry<String, RadioItem> item : RadioItemManager.itemList.entrySet()) {
            inventory.setItem(index, item.getValue().getItem());
            index++;
        }

        player.openInventory(inventory);

        return true;
    }
}
