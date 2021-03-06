package eu.mcone.community.inventory;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Transl;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class NavigatorInventory extends CoreInventory {


    public NavigatorInventory(Player p) {
        super("§f§lNavigator", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(p);

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.FIREWORK, 1, 0).displayName(Transl.get("community.inventory.mainstage", corePlayer)).lore(Transl.get("system.inventory.click.teleport", corePlayer)).create(), e -> {
            CommunityPlugin.getInstance().getCommunityWorld().teleportSilently(p, "buehne");
            p.closeInventory();
            CommunityPlugin.getInstance().getMessenger().sendTransl(p, "community.teleport.mainstage.message", corePlayer);
        });


        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.GOLD_BLOCK, 1, 0).displayName(Transl.get("community.inventory.premiumstage", corePlayer)).lore(Transl.get("system.inventory.click.teleport", corePlayer)).create(), e -> {
            CommunityPlugin.getInstance().getCommunityWorld().teleportSilently(p, "pbuehne");
            p.closeInventory();
            CommunityPlugin.getInstance().getMessenger().sendTransl(p, "community.teleport.premiumstage.message", corePlayer);
        });


        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.NETHER_STAR, 1, 0).displayName("§b§lSpawn").lore(Transl.get("system.inventory.click.teleport", corePlayer)).create(), e -> {
            CommunityPlugin.getInstance().getCommunityWorld().teleportSilently(p, "spawn");
            p.closeInventory();
            CommunityPlugin.getInstance().getMessenger().sendTransl(p, "community.teleport.spawn.message", corePlayer);
        });


        if (p.hasPermission("group.team") && !corePlayer.isNicked()) {
            setItem(InventorySlot.ROW_3_SLOT_9, new ItemBuilder(Material.WOOD, 1, 0).displayName("§b§lBar").lore(Transl.get("system.inventory.click.teleport", corePlayer)).create(), e -> {
                CommunityPlugin.getInstance().getCommunityWorld().teleportSilently(p, "teambar");
                p.closeInventory();
                CommunityPlugin.getInstance().getMessenger().send(p, "§aDu bist nun bei der Bar!");
            });

            setItem(InventorySlot.ROW_3_SLOT_8, new ItemBuilder(Material.CLAY, 1, 0).displayName("§b§lBühnen Dach").lore(Transl.get("system.inventory.click.teleport", corePlayer)).create(), e -> {
                CommunityPlugin.getInstance().getCommunityWorld().teleportSilently(p, "buehneUp");
                p.closeInventory();
                CommunityPlugin.getInstance().getMessenger().send(p, "§aDu bist nun auf der Bühne!");
            });
        }

        openInventory();
    }
}
