/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.community.Listener;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.utils.PlayerHider;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.core.translation.TranslationManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {


    @EventHandler
    public void on(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        TranslationManager tm = CoreSystem.getInstance().getTranslationManager();

        e.setJoinMessage(null);
        p.playEffect(p.getLocation(), org.bukkit.Effect.HAPPY_VILLAGER, 5);

        p.setWalkSpeed(0.2F);

        Bukkit.getScheduler().runTaskLater(CommunityPlugin.getSystem(), () -> {
            PlayerHider.updateHider(p);
        }, 1);

        CommunityPlugin.getInstance().getCommunityWorld().teleportSilently(p, "spawn");

        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.setGameMode(GameMode.ADVENTURE);
        p.getActivePotionEffects().clear();

        p.setMaxHealth(20);
        p.setHealth(20);
        p.setFoodLevel(20);
        CommunityPlugin.getInstance().getBackpackManager().setRankBoots(p);

        if (p.hasPermission("community.join.vanish")) {
            cp.setVanished(true);
        }
        p.getInventory().setItem(0, new ItemBuilder(Material.COMPASS, 1, 0).displayName(tm.get("community.inventories.items.navigator", cp)).create());
        p.getInventory().setItem(1, new ItemBuilder(Material.STORAGE_MINECART, 1, 0).displayName(tm.get("system.backpack.name", cp)).create());
        p.getInventory().setItem(8, new Skull(p.getName(), 1).toItemBuilder().displayName(tm.get("system.profil.name", cp)).create());
        p.getInventory().setItem(7, new ItemBuilder(Material.INK_SACK, 1, 10).displayName(tm.get("system.playerhider.name", cp)).create());

        if (p.hasPermission("community.settings")) {
            p.getInventory().setItem(6, new ItemBuilder(Material.REDSTONE_COMPARATOR, 1, 0).displayName(tm.get("community.inventorys.items.settings", cp)).create());
        }

        //only admin or HIGH RANK HIGH!!
        if (p.hasPermission("community.protective.shield.admin")) {
            p.getInventory().setItem(5, new ItemBuilder(Material.EYE_OF_ENDER, 1, 0).displayName(tm.get("community.inventorys.items.forcefield", cp)).create());
        }

    }
}
