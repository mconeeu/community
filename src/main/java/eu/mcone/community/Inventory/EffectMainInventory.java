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

public class EffectMainInventory extends CoreInventory {

    public EffectMainInventory(Player player) {
        super("§f§lMain Stage", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(player);


        if (!EffectManager.isFire()) {
            setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.FIREWORK_CHARGE, 1, 0).displayName("§c§lFire deaktiviert").lore("§7§oKlicke hier zum aktivieren").create(), e -> {
                player.closeInventory();
                CommunityPlugin.getInstance().getMessenger().send(player, "§cBühnen Feuereffekte an");
                EffectManager.setFireTrue();

            });
        } else {
            setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.FIREWORK_CHARGE, 1, 0).displayName("§a§lFire aktiviert").lore("§7§oKlicke zum deaktivieren").create(), e -> {
                player.closeInventory();
                EffectManager.setFireFalse();
                CommunityPlugin.getInstance().getMessenger().send(player, "§cBühnen Feuereffekte aus");
            });
        }


        if (!EffectManager.isSmoke()) {
            setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.FIREWORK, 1, 0).displayName("§c§lKonfetti deaktiviert").lore("§7§oKlicke hier zum aktivieren").create(), e -> {
                player.closeInventory();
                CommunityPlugin.getInstance().getMessenger().send(player, "§cBühnen Konfetti an");
                EffectManager.setSmokeTrue();

            });
        } else {
            setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.FIREWORK, 1, 0).displayName("§a§lKonfetti aktiviert").lore("§7§oKlicke zum deaktivieren").create(), e -> {
                player.closeInventory();
                EffectManager.setSmokeFalse();
                CommunityPlugin.getInstance().getMessenger().send(player, "§cBühnen Konfetti aus");
            });
        }

        if (!EffectManager.isWhite()) {
            setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.SNOW_BALL, 1, 0).displayName("§c§lSchnee deaktiviert").lore("§7§oKlicke hier zum aktivieren").create(), e -> {
                player.closeInventory();
                CommunityPlugin.getInstance().getMessenger().send(player, "§cBühnen Schnee an");
                EffectManager.setWhiteTrue();

            });
        } else {
            setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.SNOW_BALL, 1, 0).displayName("§a§lSchnee aktiviert").lore("§7§oKlicke zum deaktivieren").create(), e -> {
                player.closeInventory();
                EffectManager.setWhiteFalse();
                CommunityPlugin.getInstance().getMessenger().send(player, "§cBühnen Schnee aus");
            });
        }

        if (!EffectManager.isBlass()) {
            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.GLASS, 1, 0).displayName("§c§lBlasen deaktiviert").lore("§7§oKlicke hier zum aktivieren").create(), e -> {
                player.closeInventory();
                CommunityPlugin.getInstance().getMessenger().send(player, "§cBühnen Blasen an");
                EffectManager.setBlassTrue();

            });
        } else {
            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.GLASS, 1, 0).displayName("§a§lBlasen aktiviert").lore("§7§oKlicke zum deaktivieren").create(), e -> {
                player.closeInventory();
                EffectManager.setBlassFalse();
                CommunityPlugin.getInstance().getMessenger().send(player, "§cBühnen Blasen aus");
            });
        }


        openInventory();
    }
}