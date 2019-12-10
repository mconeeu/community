/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.community.Listener;

import eu.mcone.community.CommunityPlugin;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GeneralPlayerListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();
            if (!CommunityPlugin.getInstance().getBuildSystem().hasBuildModeEnabled(p)) e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();

        if (p.getGameMode() == GameMode.CREATIVE) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();

        if (p.getGameMode() == GameMode.CREATIVE) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();

        e.setKeepInventory(true);
        e.setKeepLevel(true);
        e.setDeathMessage("");
        p.spigot().respawn();
    }

    @EventHandler
    public void onAchievementAwarded(PlayerAchievementAwardedEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);


    }
}
