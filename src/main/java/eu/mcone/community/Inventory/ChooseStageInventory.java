package eu.mcone.community.Inventory;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.core.translation.TranslationManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ChooseStageInventory extends CoreInventory {

    public ChooseStageInventory(Player player) {
        super(CoreSystem.getInstance().getTranslationManager().get("community.choosestage.title", CoreSystem.getInstance().getCorePlayer(player)), player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(player);
        TranslationManager tm = CoreSystem.getInstance().getTranslationManager();

        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.GOLD_BLOCK, 1, 0).displayName(tm.get("community.choosestage.premium.display", cp)).lore(tm.get("system.edit.message", cp)).create(), e -> {
            player.closeInventory();
            new EffectPremiumInventory(player);

        });

        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.DIAMOND_BLOCK, 1, 0).displayName(tm.get("community.choosestage.main.display", cp)).lore(tm.get("system.edit.message", cp)).create(), e -> {
            player.closeInventory();
            new EffectMainInventory(player);
        });


        openInventory();
    }
}
