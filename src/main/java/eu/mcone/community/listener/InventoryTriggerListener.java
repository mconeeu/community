/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.community.listener;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.inventory.CommunitySettingsInventory;
import eu.mcone.community.inventory.EffectMainInventory;
import eu.mcone.community.inventory.EffectPremiumInventory;
import eu.mcone.community.inventory.NavigatorInventory;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Transl;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.HotbarItem;
import eu.mcone.lobby.api.items.LobbyCategory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryTriggerListener implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock() != null) {
                Material clicked = e.getClickedBlock().getType();

                if (clicked == Material.STONE_BUTTON) {
                    if (CommunityPlugin.getInstance().getCommunityWorld().getBlockLocation("effect").equals(e.getClickedBlock().getLocation())) {
                        if (p.hasPermission("community.effectmenu")) {
                            new EffectMainInventory(p);
                        } else {
                            CommunityPlugin.getInstance().getMessenger().sendTransl(p, "community.inventorytriggerlistener.noperms");
                        }
                    }
                }

                if (clicked == Material.STONE_BUTTON) {
                    if (CommunityPlugin.getInstance().getCommunityWorld().getBlockLocation("effect-premium").equals(e.getClickedBlock().getLocation())) {
                        if (p.hasPermission("community.premium.effectmenu")) {
                            new EffectPremiumInventory(p);
                        } else {
                            CommunityPlugin.getInstance().getMessenger().sendTransl(p, "community.inventorytriggerlistener.noperms");
                        }
                    }
                }

            }
            ItemStack i = p.getItemInHand();
            if ((i == null) || (!i.hasItemMeta()) || (!i.getItemMeta().hasDisplayName())) {
                return;
            }


           if (e.getItem().equals(HotbarItem.BACKPACK)) {
                CommunityPlugin.getInstance().getBackpackManager().openBackpackInventory(LobbyCategory.STORY_ITEMS.name(), p);
            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde")) {
                e.setCancelled(true);
                p.performCommand("profile");
            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lNavigator §8» §7§oTeleportiere dich durch die Welt")) {
                new NavigatorInventory(p);
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);

            } else if (e.getItem().getItemMeta().getDisplayName().equals(Transl.get("community.inventorys.items.settings", cp))) {
                e.setCancelled(true);
                new CommunitySettingsInventory(p);
            } else if (e.getItem().getType() == Material.EYE_OF_ENDER) {
                if (e.getAction() == Action.RIGHT_CLICK_AIR | e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (p.hasPermission("community.shield")) {
                        e.setCancelled(true);
                        CommunityPlugin.getInstance().getShieldManager().auto(p);
                    }
                }
            }
        }

    }
}
