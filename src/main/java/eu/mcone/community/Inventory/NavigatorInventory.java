package eu.mcone.community.Inventory;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.commands.FreeEntranceCMD;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.backpack.defaults.DefaultItem;
import eu.mcone.gameapi.api.player.GamePlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class NavigatorInventory extends CoreInventory {


    public NavigatorInventory(Player p) {
        super("§f§lNavigator", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);
        GamePlayer gamePlayer = CommunityPlugin.getInstance().getGamePlayer(p);


        if (p.hasPermission("community.bypass.entrance") || gamePlayer.hasDefaultItem(DefaultItem.FESTIVAL_ENTRANCE) || FreeEntranceCMD.freeEntrance) {
            setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.FIREWORK_CHARGE, 1, 0).displayName("§b§lBühne").lore("§7§oKlicke zum Telepotieren").create(), e -> {
                CommunityPlugin.getInstance().getCommunityWorld().teleportSilently(p, "buehne");
                p.closeInventory();
                CommunityPlugin.getInstance().getMessager().send(p, "§aDu bist nun bei der Bühne!");
            });
        } else {
            setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.FIREWORK_CHARGE, 1, 0).displayName("§b§lTicket Eingang").lore("§7§oKlicke zum Telepotieren").create(), e -> {
                p.closeInventory();
                CommunityPlugin.getInstance().getMessager().send(p, "§aDu bist nun beim Ticket Eingang!");
                CommunityPlugin.getInstance().getCommunityWorld().teleportSilently(p, "entrance-out");
            });
        }

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.NETHER_STAR, 1, 0).displayName("§b§lSpawn").lore("§7§oKlicke zum Telepotieren").create(), e -> {
            CommunityPlugin.getInstance().getCommunityWorld().teleportSilently(p, "spawn");
            p.closeInventory();
            CommunityPlugin.getInstance().getMessager().send(p, "§aDu bist nun beim Spawn!");
        });

        openInventory();
    }
}
