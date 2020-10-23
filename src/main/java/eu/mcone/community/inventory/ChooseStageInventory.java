package eu.mcone.community.inventory;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ChooseStageInventory extends CoreInventory {

    public ChooseStageInventory(Player player) {
        super("§f§lAuswahl", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(player);


        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.GOLD_BLOCK, 1, 0).displayName("§c§lPremium Stage").lore("§7§oKlicke hier zum beatbeiten").create(), e -> {
            player.closeInventory();
            new EffectPremiumInventory(player);

        });

        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.DIAMOND_BLOCK, 1, 0).displayName("§c§lMain Stage").lore("§7§oKlicke hier zum beatbeiten").create(), e -> {
            player.closeInventory();
            new EffectMainInventory(player);
        });


        openInventory();
    }
}
