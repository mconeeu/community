package eu.mcone.community.listener;

import eu.mcone.community.utils.EffectManager;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void on(PlayerQuitEvent e) {
        if (InventoryTriggerListener.run.containsKey(e.getPlayer())) {
            InventoryTriggerListener.run.get(e.getPlayer()).cancel();
            InventoryTriggerListener.run.remove(e.getPlayer());


          /*  if (Bukkit.getOnlinePlayers().size() == 1) {
                EffectManager.cancelAllTask();
            }

           */
        }
    }
}
