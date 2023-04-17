package me.mrenxo.display.npc;

import org.bukkit.entity.Player;

public abstract class NPCConversation {


    protected transient Player player;

    public final transient String npcName;

    public NPCConversation(String name) {
        this.npcName = name;
    }

    public void setPlayer(Player player) {
        if (this.player == null) this.player = player;
    }

    public abstract void execute();

}
