package me.mrenxo.item;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;

import java.lang.reflect.Type;

public enum AbilityType {
    passive,
    rightClick,
    rightClickOnBlock,
    rightClickOnEntity,
    rightClickOnPlayer,
    leftClick,
    leftClickOnBlock,
    leftClickOnEntity,
    leftClickOnPlayer,
    damaged,
    attack,
    breakBlock,
    placeBlock

}
