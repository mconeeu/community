package eu.mcone.community.listener;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.event.player.PlayerVanishEvent;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class VanishListener implements Listener {

    @EventHandler
    public void onVanish(PlayerVanishEvent e) {
        CorePlayer player = e.getPlayer();

        if (e.isVanished()) {
            if (InventoryTriggerListener.run.containsKey(player.bukkit())) {
                InventoryTriggerListener.run.get(player.bukkit()).cancel();
                InventoryTriggerListener.run.remove(player.bukkit());
                CommunityPlugin.getInstance().getMessenger().sendTransl(player.bukkit(), "community.vanishlistener.forcefield.deactivated");
            }
        }

    }
}
