package eu.mcone.community.listener;

import eu.mcone.community.inventory.PlayerHiderInventory;
import eu.mcone.community.utils.vanish.VanishManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class PlayerVanishListener implements Listener {

    private final VanishManager manager;

    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack i = e.getItem();
            if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
                return;
            }

            for (eu.mcone.community.utils.vanish.VanishPlayerVisibility target : eu.mcone.community.utils.vanish.VanishPlayerVisibility.values()) {
                if (i.equals(target.getItem().create())) {
                    new PlayerHiderInventory(p, manager);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        manager.playerLeaved(e.getPlayer());
    }

}
