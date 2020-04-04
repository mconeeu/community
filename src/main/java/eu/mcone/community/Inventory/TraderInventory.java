package eu.mcone.community.Inventory;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.backpack.defaults.DefaultItem;
import eu.mcone.gameapi.api.player.GamePlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class TraderInventory extends CoreInventory {

    public TraderInventory(Player p) {
        super("§e§lHändler", p, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        GamePlayer gamePlayer = CommunityPlugin.getInstance().getGamePlayer(p);
        CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(p);


        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.PAPER, 1, 0).displayName("§fFestival Eintrittskarte").lore("§615 Coins", "", "§fGültig bis 2022").create(), e -> {
            if (p.hasPermission("community.bypass.entrance")) {
                CommunityPlugin.getInstance().getMessager().send(p, "§cDu brauchst kein Festival Ticket!");
                return;
            } else if (gamePlayer.hasDefaultItem(DefaultItem.FESTIVAL_ENTRANCE)) {
                CommunityPlugin.getInstance().getMessager().send(p, "§cDu hast die Eintrittskarte bereits!");
                return;
            } else if (corePlayer.getCoins() >= 15) {
                CommunityPlugin.getInstance().getMessager().send(p, "§aDu hast dir erfolgreich die Festival Eintrittskarte gekauft!");
                gamePlayer.addDefaultItem(DefaultItem.FESTIVAL_ENTRANCE);
                corePlayer.removeCoins(15);
            } else {
                CommunityPlugin.getInstance().getMessager().send(p, "§cDu hast nicht genügend Coins!");
            }


            p.closeInventory();
        });


        openInventory();
    }
}
