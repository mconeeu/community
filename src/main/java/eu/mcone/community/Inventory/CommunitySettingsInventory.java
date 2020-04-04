package eu.mcone.community.Inventory;

import eu.mcone.community.CommunityPlugin;
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
                CommunityPlugin.getInstance().getMessager().send(p, "§cDu kannst nun nicht mehr fliegen");
            });
        } else {
            setItem(InventorySlot.ROW_2_SLOT_2, new ItemBuilder(Material.FEATHER, 1, 0).displayName("§c§lFlugmodus deaktiviert").lore("§7§oKlicke hier zum aktivieren").create(), e -> {
                p.closeInventory();
                CommunityPlugin.getInstance().getMessager().send(p, "§aDu kannst nun fliegen");
                p.setAllowFlight(true);
                p.setFlying(true);

            });
        }
        if (p.hasPermission("community.build")) {
            if (CommunityPlugin.getInstance().getBuildSystem().hasBuildModeEnabled(p)) {
                setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.GRASS, 1, 0).displayName("§a§lBuildmodus aktiviert").lore("§7§oKlicke zum deaktivieren").create(), e -> {
                    p.closeInventory();
                    CommunityPlugin.getInstance().getBuildSystem().changeBuildMode(p);
                });
            } else {
                setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.GRASS, 1, 0).displayName("§c§lBuildmodus deaktiviert").lore("§7§oKlicke hier zum aktivieren").create(), e -> {
                    p.closeInventory();
                    CommunityPlugin.getInstance().getBuildSystem().changeBuildMode(p);
                });
            }

        }
        if (cp.isVanished()) {
            setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.POTION, 1, 0).displayName("§a§lVanish-Modus aktiviert").lore("§7§oKlicke zum deaktivieren").create(), e -> {
                p.closeInventory();
                cp.setVanished(false);
            });
        } else {
            setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.POTION, 1, 0).displayName("§c§lVanish-Modus deaktiviert").lore("§7§oKlicke hier zum aktivieren").create(), e -> {
                p.closeInventory();
                cp.setVanished(true);
            });
        }

        if (p.hasPermission("community.effectmenu")) {
            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.FIREWORK_CHARGE, 1, 0).displayName("§a§lBühnen Effekte").lore("§7§oKlicke zum bearbeiten").create(), e -> {
                p.closeInventory();
                new EffectInventory(p);
            });
        }

        openInventory();
    }
}
