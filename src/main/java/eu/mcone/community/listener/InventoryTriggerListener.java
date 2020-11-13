/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.community.listener;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.inventory.*;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
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


    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();


        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock() != null) {
                Material clicked = e.getClickedBlock().getType();

                if (clicked == Material.STONE_BUTTON) {
                    if (CommunityPlugin.getInstance().getCommunityWorld().getBlockLocation("effect").equals(e.getClickedBlock().getLocation())) {
                        if (p.hasPermission("community.effectmenu")) {
                            new EffectMainInventory(p);
                        } else {
                            CommunityPlugin.getInstance().getMessenger().send(p, "§4Du hast dafür keine Berechtigung!");
                        }
                    }
                }

                if (clicked == Material.STONE_BUTTON) {
                    if (CommunityPlugin.getInstance().getCommunityWorld().getBlockLocation("effect-premium").equals(e.getClickedBlock().getLocation())) {
                        if (p.hasPermission("community.premium.effectmenu")) {
                            new EffectPremiumInventory(p);
                        } else {
                            CommunityPlugin.getInstance().getMessenger().send(p, "§4Du hast dafür keine Berechtigung!");
                        }
                    }
                }

            }
            ItemStack i = p.getItemInHand();
            if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
                return;
            }


          /* if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lRucksack §8» §7§oZeige deine gesammelten Items an")) {
                LobbyPlugin.getInstance().getMessenger().send(p, "§4Hier ist etwas schief gelaufen...");
                //    CommunityPlugin.getInstance().getBackpackManager().openBackpackInventory(LobbyCategory.STORY_ITEMS.name(), p);
            } else
           */
             if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde")) {
                 e.setCancelled(true);
                 p.performCommand("profile");
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
                    CommunityPlugin.getInstance().getShieldManager().auto(p);
                }
            }
        }

    }
}
