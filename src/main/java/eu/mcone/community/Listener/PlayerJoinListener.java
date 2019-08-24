/*
 * Copyright (c) 2017 - 2019 Rufus Maiwald, Marvin Hülsmann, Dominik Lippl and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.community.Listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gamesystem.api.enums.Item;
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

        e.setJoinMessage(null);


        p.playEffect(p.getLocation(), org.bukkit.Effect.HAPPY_VILLAGER, 5);

        p.setWalkSpeed(0.2F);

        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.setGameMode(GameMode.ADVENTURE);
        p.getActivePotionEffects().clear();

        p.setMaxHealth(20);
        p.setHealth(20);
        p.setFoodLevel(20);

        if (p.hasPermission("community.join.vanish")) {
            cp.setVanished(true);
        }


        p.getInventory().setItem(8, new Skull(p.getName(), 1).toItemBuilder().displayName("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde").create());
        p.getInventory().setItem(7, new ItemBuilder(Material.INK_SACK, 1, 10).displayName("§3§lSpieler Verstecken §8» §7§oBlende alle anderen Spieler aus").create());
        p.getInventory().setItem(0, new ItemBuilder(Material.COMPASS, 1, 0).displayName("§3§lNavigator §8» §7§oTelepotiere dich durch die Welt").create());
        p.getInventory().setItem(6, new ItemBuilder(Material.REDSTONE_COMPARATOR,1,0).displayName("§3§lCommunity Einstellungen §8» §7§oBearbeite Team einstellungen").create());
        p.getInventory().setItem(1, new ItemBuilder(Material.STORAGE_MINECART, 1, 0).displayName("§3§lRucksack §8» §7§oZeige deine gesammelten Items an").create());

        switch (cp.getMainGroup()) {
            case PREMIUM:
                p.getInventory().setBoots(Item.PREMIUM_BOOTS.getItemStack());
                break;
            case PREMIUMPLUS:
                p.getInventory().setBoots(Item.PREMIUM_PLUS_BOOTS.getItemStack());
                break;
            case YOUTUBER:
                p.getInventory().setBoots(Item.YOUTUBER_BOOTS.getItemStack());
                break;
            case JRSUPPORTER:
                p.getInventory().setBoots(Item.JR_SUPPORTER_BOOTS.getItemStack());
                break;
            case SUPPORTER:
                p.getInventory().setBoots(Item.SUPPORTER_BOOTS.getItemStack());
                break;
            case MODERATOR:
                p.getInventory().setBoots(Item.MODERATOR_BOOTS.getItemStack());
                break;
            case SRMODERATOR:
                p.getInventory().setBoots(Item.SR_MODERATOR_BOOTS.getItemStack());
                break;
            case BUILDER:
                p.getInventory().setBoots(Item.BUILDER_BOOTS.getItemStack());
                break;
            case DEVELOPER:
                p.getInventory().setBoots(Item.DEVELOPER_BOOTS.getItemStack());
                break;
            case ADMIN:
                p.getInventory().setBoots(Item.ADMIN_BOOTS.getItemStack());
                break;
        }
    }
}
