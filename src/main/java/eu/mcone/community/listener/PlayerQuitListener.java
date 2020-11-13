package eu.mcone.community.listener;

import eu.mcone.community.CommunityPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void on(PlayerQuitEvent e) {
        if (CommunityPlugin.getInstance().getShieldManager().getRun().containsKey(e.getPlayer())) {
            CommunityPlugin.getInstance().getShieldManager().getRun().get(e.getPlayer()).cancel();
            CommunityPlugin.getInstance().getShieldManager().getRun().remove(e.getPlayer());

            CommunityPlugin.getInstance().getVanishManager().playerLeaved(e.getPlayer());

        }
    }
}
