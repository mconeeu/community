package eu.mcone.community.inventory;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.utils.effects.StageEffects;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import org.bukkit.entity.Player;

public class EffectPremiumInventory extends CoreInventory {

    public EffectPremiumInventory(Player player) {
        super("§f§lPremium Stage", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(player);


        int i = InventorySlot.ROW_2_SLOT_2;

        for (StageEffects stageEffects : StageEffects.values()) {
            if (!stageEffects.getMainStage()) {
                setItem(i, stageEffects.getItem(), e -> {
                    player.closeInventory();
                    CommunityPlugin.getInstance().getStageEffectManager().auto(player, stageEffects);
                });
            }
        }

        openInventory();
    }
}
