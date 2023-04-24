package me.mrenxo.item;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;

import java.lang.reflect.Type;

public enum AbilityType {
    passive("PASSIVE"),
    rightClick("RIGHT-CLICK"),
    rightClickOnBlock("RIGHT-CLICK"),
    rightClickOnEntity("RIGHT-CLICK"),
    rightClickOnPlayer("RIGHT-CLICK"),
    leftClick("LEFT-CLICK"),
    leftClickOnBlock("LEFT-CLICK"),
    leftClickOnEntity("LEFT-CLICK"),
    leftClickOnPlayer("LEFT-CLICK"),
    damaged("DAMAGED"),
    attack("ATTACK"),
    breakBlock("BREAK"),
    placeBlock("PLACE");

    public final String name;

    AbilityType(String name) {
        this.name = name;
    }

}
