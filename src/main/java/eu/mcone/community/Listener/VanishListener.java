package eu.mcone.community.Listener;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.event.PlayerVanishEvent;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
                CommunityPlugin.getInstance().getMessenger().send((CommandSender) player, "§4Das Schutzschild wurde deaktivert, weil du den Vanish Modus betreten hast!");
            }
        }

    }
}
