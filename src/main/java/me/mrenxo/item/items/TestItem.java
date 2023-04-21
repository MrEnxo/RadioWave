package me.mrenxo.item.items;

import me.mrenxo.display.Formatter;
import me.mrenxo.item.Ability;
import me.mrenxo.item.AbilityType;
import me.mrenxo.item.RadioItem;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

import static me.mrenxo.display.Formatter.color;

public class TestItem extends RadioItem {
    public TestItem() {
        super("test", List.of("sussy"), Material.DIRT, List.of() ,List.of(
                new Ability<>(
                        "test",
                        List.of("test"),
                        AbilityType.rightClick,
                        true,
                        TestItem::testAbilityRightClick
                )
        ));
    }

    private static void testAbilityRightClick(PlayerInteractEvent e) {
        e.getPlayer().sendMessage(color("baby dont hurt me"));
    }
}
