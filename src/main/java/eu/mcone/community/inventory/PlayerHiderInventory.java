package eu.mcone.community.inventory;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.utils.vanish.VanishManager;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.gameapi.api.GameAPI;
import eu.mcone.lobby.api.LobbyPlugin;
import eu.mcone.lobby.api.player.vanish.VanishPlayerVisibility;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class PlayerHiderInventory extends CoreInventory {

    private final Map<Player, eu.mcone.community.utils.vanish.VanishPlayerVisibility> hiddenPlayers = new HashMap<>();

    public PlayerHiderInventory(Player player, VanishManager manager) {
        super("§8» §a§lSpielersichtbarkeit", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        setItem(InventorySlot.ROW_2_SLOT_3, eu.mcone.community.utils.vanish.VanishPlayerVisibility.EVERYBODY.getItem(), e -> {
            updateHiderSystem(player, eu.mcone.community.utils.vanish.VanishPlayerVisibility.EVERYBODY, manager);
        });

        setItem(InventorySlot.ROW_2_SLOT_5, eu.mcone.community.utils.vanish.VanishPlayerVisibility.ONLY_VIPS.getItem(), e -> {
            updateHiderSystem(player, eu.mcone.community.utils.vanish.VanishPlayerVisibility.ONLY_VIPS, manager);
        });

        setItem(InventorySlot.ROW_2_SLOT_7, eu.mcone.community.utils.vanish.VanishPlayerVisibility.NOBODY.getItem(), e -> {
            updateHiderSystem(player, eu.mcone.community.utils.vanish.VanishPlayerVisibility.NOBODY, manager);
        });

        openInventory();

    }

    private void updateHiderSystem(Player p, eu.mcone.community.utils.vanish.VanishPlayerVisibility target, VanishManager vanishManager) {
        player.closeInventory();

        if (!CoreSystem.getInstance().getCooldownSystem().addAndCheck(getClass(), p.getUniqueId())) {
            // CommunityPlugin.getInstance().getMessenger().sendError(p, "§4Bitte warte kurz, bevor du erneut die Sichbarkeit von Spielern veränderst!");
            return;
        }

        vanishManager.setVanishPlayerVisibility(player, target);


        p.getInventory().setItem(InventorySlot.ROW_1_SLOT_8, CommunityPlugin.getInstance().getVanishManager().getVanishPlayerVisibility(p).getItem());
    }
}
