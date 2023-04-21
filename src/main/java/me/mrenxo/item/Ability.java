package me.mrenxo.item;

import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Ability<T extends Event> {

    public final String name;
    public final List<String> description;

    public final AbilityType type;

    public final boolean visible;

    public final Consumer<T> run;

    public Ability(String name, List<String> description, AbilityType type, boolean visible, Consumer<T> run) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.visible = visible;
        this.run = run;
    }

    public void activateAbility(T data) {
        run.accept(data);
    }

}
