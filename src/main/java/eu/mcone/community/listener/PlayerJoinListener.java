package eu.mcone.community.listener;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.event.player.CorePlayerLoadedEvent;
import eu.mcone.gameapi.api.event.player.GamePlayerLoadedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {


    @EventHandler
    public void on(PlayerJoinEvent e) {
        e.setJoinMessage(null);
    }

    @EventHandler
    public void on(GamePlayerLoadedEvent e) {
        if (e.getCorePlayerLoadedEvent().getLoadReason().equals(CorePlayerLoadedEvent.Reason.JOIN)) {
            CommunityPlugin.getInstance().getCommunityWorld().teleportSilently(e.getBukkitPlayer(), "spawn");
        }

        CommunityPlugin.getInstance().getVanishManager().updateInventory(e.getCorePlayer());
        CommunityPlugin.getInstance().getTimeManager().setCurrentRealTime(e.getPlayer());
    }
}
