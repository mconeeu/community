package eu.mcone.community.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void on(PlayerQuitEvent e) {
        if (InventoryTriggerListener.run.containsKey(e.getPlayer())) {
            InventoryTriggerListener.run.get(e.getPlayer()).cancel();
            InventoryTriggerListener.run.remove(e.getPlayer());
        }
    }
}
