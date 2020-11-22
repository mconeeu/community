package eu.mcone.community.utils;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import lombok.Getter;

@Getter
public enum Captures {

    C2("c-2", "c-2"),
    C3("c-3", "c-3"),
    C4("c-4", "c-4"),
    C5("c-5", "c-5"),
    C6("c-6", "c-6"),
    C7("c-7", "c-7"),
    C8("c-8", "c-8"),
    C9("c-9", "c-9"),
    C10("c-10", "c-10");


    private final String capture, npcName;

    Captures(String capture, String npcName) {
        this.capture = capture;
        this.npcName = npcName;
    }

    public PlayerNpc getNpc() {
        return (PlayerNpc) CommunityPlugin.getInstance().getCommunityWorld().getNPC(npcName);
    }

}