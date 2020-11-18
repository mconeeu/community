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
import eu.mcone.community.utils.PlayerHider;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Transl;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.lobby.api.items.LobbyCategory;
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

            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Transl.get("system.backpack.name", cp))) {
                e.setCancelled(true);
                CommunityPlugin.getInstance().getBackpackManager().openBackpackInventory(LobbyCategory.STORY_ITEMS.name(), p);
            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Transl.get("system.profil.name", cp))) {
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
                    if (!cp.isVanished()) {
                        e.setCancelled(true);
                        if (run.containsKey(p)) {
                            CommunityPlugin.getInstance().getMessenger().send(p, "§cDas Schutzschild wurde deaktiviert");
                            p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 2F, 0.5F);
                            run.get(p).cancel();
                            run.remove(p);
                        } else if (!run.containsKey(p)) {
                            run.put(p, new BukkitRunnable() {


                                @Override
                                public void run() {
                                    p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 100, 100);
                                    p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 100, 100);

                                }

                            });
                            run.get(p).runTaskTimer(CommunityPlugin.getInstance(), 20, 20);
                            CommunityPlugin.getInstance().getMessenger().send(p, "§aDas Schutzschild wurde aktiviert");
                            p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 2F, 1F);
                        }
                    } else {
                        if (run.containsKey(p)) {
                            run.get(p).cancel();
                            run.remove(p);
                        }
                        CommunityPlugin.getInstance().getMessenger().send(p, "§4Du darfst im Vanish Modus das Schutzschild nicht benutzen!");
                    }

                }
            }
        }

    }
}
