package eu.mcone.community.listener;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.event.npc.NpcInteractEvent;
import eu.mcone.coresystem.api.bukkit.npc.entity.PlayerNpc;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NpcListener implements Listener {

    @EventHandler
    public void on(NpcInteractEvent e) {

        String prefix = "§8[§7§l!§8] §cNPC §8» §fBewohner §8|§7 ";
        PlayerNpc playerNpc = (PlayerNpc) e.getNpc();


        switch (e.getNpc().getData().getName()) {
            case "c-12":
                CommunityPlugin.getInstance().getMessenger().sendSimple(e.getPlayer(), prefix + "Der MCONE Server macht noch mehr Spaß mit LabyMod!");
                playEmote(true, playerNpc, e.getPlayer());
                break;
            case "c-8":
                CommunityPlugin.getInstance().getMessenger().sendSimple(e.getPlayer(), prefix + "Wo ist das OneGaming Head Quarter?");
                playEmote(false, playerNpc, e.getPlayer());
                break;
            case "c-10":
                CommunityPlugin.getInstance().getMessenger().sendSimple(e.getPlayer(), prefix + "Guter Server, Gute Entwicklung, weiter so!");
                playEmote(true, playerNpc, e.getPlayer());
                break;
            case "c-9":
                CommunityPlugin.getInstance().getMessenger().sendSimple(e.getPlayer(), prefix + "Wie wurden denn die Bewohner beweglich gemacht? Ist doch bestimmt schwer oder?");
                playEmote(true, playerNpc, e.getPlayer());
                break;
            case "c-5":
                CommunityPlugin.getInstance().getMessenger().sendSimple(e.getPlayer(), prefix + "Ui dieses Festival ist mir ein bisschen zu laut!");
                playEmote(true, playerNpc, e.getPlayer());
                break;
            case "c-11":
                CommunityPlugin.getInstance().getMessenger().sendSimple(e.getPlayer(), prefix + "Kennt ihr schon AllInOne? Bitte direkt installieren ohne wiederworte! (Crissi stinkt)");
                playEmote(true, playerNpc, e.getPlayer());
                break;
            case "c-6":
                CommunityPlugin.getInstance().getMessenger().sendSimple(e.getPlayer(), prefix + "Ich mag Capri Sonne!");
                playEmote(false, playerNpc, e.getPlayer());
                break;
        }
    }


    private void playEmote(boolean normal, PlayerNpc playerNpc, Player player) {
        if (normal) {
            playerNpc.playLabymodEmote(4, player);
        } else {
            playerNpc.playLabymodEmote(2, player);
        }
    }
}
