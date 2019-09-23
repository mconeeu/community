package eu.mcone.community.Inventory;

import eu.mcone.community.Community;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
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
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);


        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.FIREWORK_CHARGE, 1, 0).displayName("§b§lBühne").lore("§7§oKlicke zum Telepotieren").create(), e -> {
            Community.getInstance().getCommunityWorld().teleportSilently(p, "buehne");
            p.closeInventory();
            Community.getInstance().getMessager().send(p, "§aDu bist nun bei der Bühne!");
            Community.getInstance().getCommunityWorld().teleportSilently(p,"buehne");
        });

        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.NETHER_STAR, 1, 0).displayName("§b§lSpawn").lore("§7§oKlicke zum Telepotieren").create(), e -> {
            Community.getInstance().getCommunityWorld().teleportSilently(p, "spawn");
            p.closeInventory();
            Community.getInstance().getMessager().send(p, "§aDu bist nun beim Spawn!");
        });

        openInventory();
    }
}
