package eu.mcone.community.inventory;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Transl;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ChooseStageInventory extends CoreInventory {

    public ChooseStageInventory(Player player) {
        super(Transl.get("community.choosestage.title", CoreSystem.getInstance().getCorePlayer(player)), player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(player);

        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.GOLD_BLOCK, 1, 0).displayName(Transl.get("community.choosestage.premium.display", cp)).lore(Transl.get("system.edit.message", cp)).create(), e -> {
            player.closeInventory();
            new EffectPremiumInventory(player);

        });

        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.DIAMOND_BLOCK, 1, 0).displayName(Transl.get("community.choosestage.main.display", cp)).lore(Transl.get("system.edit.message", cp)).create(), e -> {
            player.closeInventory();
            new EffectMainInventory(player);
        });


        setItem(InventorySlot.ROW_3_SLOT_9, new ItemBuilder(Material.BARRIER, 1).displayName("§4§lAlles deaktivieren").create(), e -> {
            player.closeInventory();
            CommunityPlugin.getInstance().getStageEffectManager().clearAllEffects();
            CommunityPlugin.getInstance().getMessenger().send(player, "§2Du hast alle §aEfekte§2 ausgeschaltet!");
        });


        openInventory();
    }
}
