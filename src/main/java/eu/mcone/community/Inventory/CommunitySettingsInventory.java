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

public class CommunitySettingsInventory extends CoreInventory {


    public CommunitySettingsInventory(Player p) {
        super("§f§lCommunity Einstellung", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(p);

        if (p.getAllowFlight()) {
            setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.FEATHER, 1, 0).displayName("§a§lFlugmodus aktiviert").lore("§7§oKlicke zum deaktivieren").create(), e -> {
                p.closeInventory();
                p.setAllowFlight(false);
                p.setFlying(false);
                Community.getInstance().getMessager().send(p, "§cDu kannst nun nicht mehr fliegen");
            });
        } else {
            setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.FEATHER, 1, 0).displayName("§d§lFlugmodus deaktiviert").lore("§7§oKlicke hier zum aktivieren").create(), e -> {
                p.closeInventory();
                Community.getInstance().getMessager().send(p, "§aDu kannst nun fliegen");
                p.setAllowFlight(true);
                p.setFlying(true);
            });

        }
        openInventory();
    }
}
