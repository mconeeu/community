package eu.mcone.community.inventory;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.utils.events.Event;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import org.bukkit.entity.Player;

public class EventInventory extends CoreInventory {

    public EventInventory(Player player) {
        super("§fEvents", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);


        int i = InventorySlot.ROW_2_SLOT_2;
        for (Event event : Event.values()) {

            setItem(i, event.getItem().lore("§7§oKlicke zum starten/stoppen").create(), e -> {
                CommunityPlugin.getInstance().getEventManager().auto(player, event);
                player.closeInventory();
            });

            i++;
        }


        openInventory();
    }
}
