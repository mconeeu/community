/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.community.Listener;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.Inventory.CommunitySettingsInventory;
import eu.mcone.community.Inventory.NavigatorInventory;
import eu.mcone.community.player.CommunityPlayer;
import eu.mcone.community.utils.PlayerHider;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.category.CategoryInventory;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.GamePlugin;
import eu.mcone.gameapi.api.backpack.BackpackItem;
import eu.mcone.gameapi.api.backpack.BackpackManager;
import eu.mcone.gameapi.api.backpack.Category;
import eu.mcone.lobby.api.enums.LobbyCategory;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class InventoryTriggerListener implements Listener {

    public static HashMap<Player, BukkitRunnable> run = new HashMap<>();

    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack i = p.getItemInHand();
            if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
                return;
            }

            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lRucksack §8» §7§oZeige deine gesammelten Items an")) {
                e.setCancelled(true);
                CommunityPlugin.getInstance().getBackpackManager().openBackpackInventory(LobbyCategory.STORY_ITEMS.name(), p);
            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde")) {
                e.setCancelled(true);
                p.performCommand("profile");
            } else if (p.getItemInHand().getItemMeta().getDisplayName().equals("§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus")) {
                e.setCancelled(true);
                PlayerHider.hidePlayers(p);
            } else if (p.getItemInHand().getItemMeta().getDisplayName().equals("§3§lSpieler Anzeigen §8» §7§oZeigt alle Spieler wieder an")) {
                e.setCancelled(true);
                PlayerHider.showPlayers(p);
            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lNavigator §8» §7§oTelepotiere dich durch die Welt")) {
                new NavigatorInventory(p);
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);

            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lCommunity Einstellungen §8» §7§oBearbeite Team einstellungen")) {
                e.setCancelled(true);
                new CommunitySettingsInventory(p);
            } else if (e.getItem().getType() == Material.EYE_OF_ENDER) {
                if (e.getAction() == Action.RIGHT_CLICK_AIR | e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    e.setCancelled(true);
                    if (run.containsKey(p)) {
                        CommunityPlugin.getInstance().getMessager().send(p, "§cDas Schutzschild wurde deaktiviert");
                        p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 2F, 0.5F);
                        run.get(p).cancel();
                        run.remove(p);
                    } else if (!run.containsKey(p)) {
                        run.put(p, new BukkitRunnable() {


                            @Override
                            public void run() {
                                p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 100,100);
                                p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 100,100);

                            }

                        });
                        run.get(p).runTaskTimer(CommunityPlugin.getInstance(), 20, 20);
                        CommunityPlugin.getInstance().getMessager().send(p,"§aDas Schutzschild wurde aktiviert");
                        p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 2F, 1F);
                    }

                }
            }
        }

    }
}
