package eu.mcone.community.inventory;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.utils.EffectManager;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class EffectPremiumInventory extends CoreInventory {

    public EffectPremiumInventory(Player player) {
        super("§f§lPremium Stage", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(player);


        if (!EffectManager.isFirePremium()) {
            setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.FIREWORK_CHARGE, 1, 0).displayName("§c§lFire deaktiviert").lore("§7§oKlicke hier zum aktivieren").create(), e -> {
                player.closeInventory();
                CommunityPlugin.getInstance().getMessenger().send(player, "§cBühnen Feuereffekte an");
                EffectManager.setFirePremiumTrue();

            });
        } else {
            setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.FIREWORK_CHARGE, 1, 0).displayName("§a§lFire aktiviert").lore("§7§oKlicke zum deaktivieren").create(), e -> {
                player.closeInventory();
                EffectManager.setFirePremiumFalse();
                CommunityPlugin.getInstance().getMessenger().send(player, "§cBühnen Feuereffekte aus");
            });
        }


        openInventory();
    }
}
