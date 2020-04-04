package eu.mcone.community.Inventory;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.utils.EffectManager;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class EffectInventory extends CoreInventory {

    public EffectInventory(Player player) {
        super("§f§lBühne", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(player);


        if (!EffectManager.isFire()) {
            setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.FIREWORK_CHARGE, 1, 0).displayName("§c§lFire deaktiviert").lore("§7§oKlicke hier zum aktivieren").create(), e -> {
                player.closeInventory();
                CommunityPlugin.getInstance().getMessager().send(player, "§cBühnen Feuereffekte an");
                EffectManager.setFireTrue();

            });
        } else {
            setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.FIREWORK_CHARGE, 1, 0).displayName("§a§lFire aktiviert").lore("§7§oKlicke zum deaktivieren").create(), e -> {
                player.closeInventory();
                EffectManager.setFireFalse();
                CommunityPlugin.getInstance().getMessager().send(player, "§cBühnen Feuereffekte aus");
            });
        }


        if (!EffectManager.isSmoke()) {
            setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.SMOOTH_BRICK, 1, 0).displayName("§c§lSmoke deaktiviert").lore("§7§oKlicke hier zum aktivieren").create(), e -> {
                player.closeInventory();
                CommunityPlugin.getInstance().getMessager().send(player, "§cBühnen Nebeleffekte an");
                EffectManager.setSmokeTrue();

            });
        } else {
            setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.SMOOTH_BRICK, 1, 0).displayName("§a§lSmoke aktiviert").lore("§7§oKlicke zum deaktivieren").create(), e -> {
                player.closeInventory();
                EffectManager.setSmokeFalse();
                CommunityPlugin.getInstance().getMessager().send(player, "§cBühnen Nebeleffekte aus");
            });
        }


        openInventory();
    }
}