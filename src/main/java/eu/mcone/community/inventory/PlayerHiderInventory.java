package eu.mcone.community.inventory;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.utils.vanish.VanishManager;
import eu.mcone.community.utils.vanish.VanishPlayerVisibility;
import eu.mcone.coresystem.api.bukkit.facades.Transl;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import org.bukkit.entity.Player;

public class PlayerHiderInventory extends CoreInventory {


    public PlayerHiderInventory(Player player, VanishManager manager) {
        super(Transl.get("system.inventory.playerhider", player), player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        setItem(InventorySlot.ROW_2_SLOT_3, eu.mcone.community.utils.vanish.VanishPlayerVisibility.EVERYBODY.getItem().displayName(Transl.get("system.inventory.playerhider.all")).create(), e -> {
            updateHiderSystem(player, eu.mcone.community.utils.vanish.VanishPlayerVisibility.EVERYBODY, manager);
        });

        setItem(InventorySlot.ROW_2_SLOT_5, eu.mcone.community.utils.vanish.VanishPlayerVisibility.ONLY_VIPS.getItem().displayName(Transl.get("system.inventory.playerhider.vip")).create(), e -> {
            updateHiderSystem(player, eu.mcone.community.utils.vanish.VanishPlayerVisibility.ONLY_VIPS, manager);
        });

        setItem(InventorySlot.ROW_2_SLOT_7, eu.mcone.community.utils.vanish.VanishPlayerVisibility.NOBODY.getItem().displayName(Transl.get("system.inventory.playerhider.none")).create(), e -> {
            updateHiderSystem(player, eu.mcone.community.utils.vanish.VanishPlayerVisibility.NOBODY, manager);
        });

        openInventory();

    }

    private void updateHiderSystem(Player p, eu.mcone.community.utils.vanish.VanishPlayerVisibility target, VanishManager vanishManager) {
        player.closeInventory();

        vanishManager.setVanishPlayerVisibility(player, target);
        if (CommunityPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(p).equals(VanishPlayerVisibility.EVERYBODY)) {
            p.getInventory().setItem(InventorySlot.ROW_1_SLOT_8, CommunityPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(p).getItem().displayName(Transl.get("system.inventory.playerhider.all")).create());
        } else if (CommunityPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(p).equals(VanishPlayerVisibility.ONLY_VIPS)) {
            p.getInventory().setItem(InventorySlot.ROW_1_SLOT_8, CommunityPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(p).getItem().displayName(Transl.get("system.inventory.playerhider.vip")).create());
        } else {
            p.getInventory().setItem(InventorySlot.ROW_1_SLOT_8, CommunityPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(p).getItem().displayName(Transl.get("system.inventory.playerhider.none")).create());
        }

    }
}
