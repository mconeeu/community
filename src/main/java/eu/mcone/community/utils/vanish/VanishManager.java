package eu.mcone.community.utils.vanish;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.listener.PlayerVanishListener;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.facades.Transl;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.item.Skull;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.core.player.Group;
import eu.mcone.coresystem.api.core.player.SkinInfo;
import eu.mcone.gameapi.api.GameAPI;
import eu.mcone.gameapi.api.HotbarItem;
import eu.mcone.gameapi.api.player.GamePlayer;
import lombok.Getter;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class VanishManager {

    @Getter
    private final Map<Player, eu.mcone.community.utils.vanish.VanishPlayerVisibility> hiddenPlayers;

    public VanishManager(CommunityPlugin plugin) {
        this.hiddenPlayers = new HashMap<>();

        plugin.registerEvents(new PlayerVanishListener(this));
        CoreSystem.getInstance().getVanishManager().registerVanishRule(5, (player, playerCanSee) -> {
            if (hiddenPlayers.containsKey(player) && !hiddenPlayers.get(player).equals(eu.mcone.community.utils.vanish.VanishPlayerVisibility.EVERYBODY)) {
                switch (hiddenPlayers.get(player)) {
                    case NOBODY:
                        playerCanSee.clear();
                        return;
                    case ONLY_VIPS: {
                        playerCanSee.removeIf(p -> !CoreSystem.getInstance().getCorePlayer(p).getMainGroup().standsAbove(Group.PREMIUMPLUS));
                    }
                }
            }
        });
    }

    public void setVanishPlayerVisibility(Player p, VanishPlayerVisibility target) {

        if (!hiddenPlayers.containsKey(p) || !hiddenPlayers.get(p).equals(target)) {
            hiddenPlayers.put(p, target);
            CoreSystem.getInstance().getVanishManager().recalculateVanishes();

            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
            p.playSound(p.getLocation(), Sound.LAVA_POP, 1, 1);
            p.playEffect(p.getLocation(), Effect.FIREWORKS_SPARK, 1);

            GameAPI.getInstance().getGamePlayer(p).setEffectsVisible(target.equals(eu.mcone.community.utils.vanish.VanishPlayerVisibility.EVERYBODY));

            if (target.getName().equals(VanishPlayerVisibility.EVERYBODY.getName())) {
                CommunityPlugin.getInstance().getMessenger().send(p, Transl.get("system.playerhider.visibility.change", p) + "[" + Transl.get("system.playerhider.all", p) + "]");
            } else if (target.getName().equals(VanishPlayerVisibility.ONLY_VIPS.getName())) {
                CommunityPlugin.getInstance().getMessenger().send(p, Transl.get("system.playerhider.visibility.change", p) + "[" + Transl.get("system.playerhider.vip", p) + "]");
            } else {
                CommunityPlugin.getInstance().getMessenger().send(p, Transl.get("system.playerhider.visibility.change", p) + "[" + Transl.get("system.playerhider.none", p) + "]");
            }

        }
    }

    public eu.mcone.community.utils.vanish.VanishPlayerVisibility getVanishPlayerVisibility(Player p) {
        return hiddenPlayers.getOrDefault(p, eu.mcone.community.utils.vanish.VanishPlayerVisibility.EVERYBODY);
    }

    public void updateInventory(CorePlayer cp) {
        Player p = cp.bukkit();
        p.playEffect(p.getLocation(), org.bukkit.Effect.HAPPY_VILLAGER, 5);

        p.setWalkSpeed(0.2F);

        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.setGameMode(GameMode.ADVENTURE);
        p.getActivePotionEffects().clear();

        p.setMaxHealth(20);
        p.setHealth(20);
        p.setFoodLevel(20);

        GamePlayer gamePlayer = CommunityPlugin.getInstance().getGamePlayer(cp.bukkit());

        if (gamePlayer != null) {
            Bukkit.getScheduler().runTaskLater(CommunityPlugin.getInstance(), () -> {
                CommunityPlugin.getInstance().getBackpackManager().setRankBoots(p);

                p.getInventory().setItem(0, new ItemBuilder(Material.COMPASS, 1, 0).displayName(Transl.get("community.inventorys.items.navigator", cp)).create());
                p.getInventory().setItem(1, HotbarItem.BACKPACK);

                p.getInventory().setItem(7, VanishPlayerVisibility.EVERYBODY.getItem().displayName(Transl.get("system.inventory.playerhider.all")).create());

                if (p.hasPermission("community.settings") && !cp.isNicked()) {
                    p.getInventory().setItem(6, new ItemBuilder(Material.REDSTONE_COMPARATOR, 1, 0).displayName(Transl.get("community.inventorys.items.settings", cp)).create());
                }

                if (p.hasPermission("community.shield") && !cp.isNicked()) {
                    p.getInventory().setItem(5, new ItemBuilder(Material.EYE_OF_ENDER, 1, 0).displayName(Transl.get("community.inventorys.items.forcefield", cp)).create());
                }

                p.getInventory().setItem(8, getProfile(!cp.isNicked() ? cp.getSkin() : cp.getNick().getSkinInfo(), cp).create());

            }, 5);
        }
    }

    private static ItemBuilder getProfile(SkinInfo skin, CorePlayer corePlayer) {
        String PROFILE_DISPLAY_NAME = Transl.get("system.inventorys.items.profile", corePlayer);
        return Skull.fromMojangValue(skin.getValue(), 1)
                .toItemBuilder()
                .displayName(PROFILE_DISPLAY_NAME);
    }

    public void playerLeaved(Player p) {
        hiddenPlayers.remove(p);
    }

}
