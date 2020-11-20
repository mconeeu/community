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
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.activation.CommandObject;
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

            CommunityPlugin.getInstance().getMessenger().sendSuccess(p, "Du hast die Spielersichtbarkeit auf ![" + target.getName() + "] geändert!");

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


        p.getInventory().setItem(0, new ItemBuilder(Material.COMPASS, 1, 0).displayName("§3§lNavigator §8» §7§oTeleportiere dich durch die Welt").create());
        p.getInventory().setItem(1, HotbarItem.BACKPACK);
        p.getInventory().setItem(8, new Skull(p.getName(), 1).toItemBuilder().displayName("§3§lProfil §8» §7§oEinstellungen / Stats / Freunde").create());


        p.getInventory().setItem(7, VanishPlayerVisibility.EVERYBODY.getItem());


        GamePlayer gamePlayer = CommunityPlugin.getInstance().getGamePlayer(cp.bukkit());

        if (gamePlayer != null) {
            Bukkit.getScheduler().runTaskLater(CommunityPlugin.getInstance(), () -> {
                CommunityPlugin.getInstance().getBackpackManager().setRankBoots(p);


                if (p.hasPermission("community.settings") && !cp.isNicked()) {
                    p.getInventory().setItem(6, new ItemBuilder(Material.REDSTONE_COMPARATOR, 1, 0).displayName(Transl.get("community.inventorys.items.settings", cp)).create());
                }

                //only admin or HIGH RANK HIGH!!
                if (p.hasPermission("community.shield") && !cp.isNicked()) {
                    p.getInventory().setItem(5, new ItemBuilder(Material.EYE_OF_ENDER, 1, 0).displayName(Transl.get("community.inventorys.items.forcefield", cp)).create());
                }

                p.getInventory().setItem(8, getProfile(!cp.isNicked() ? cp.getSkin() : cp.getNick().getSkinInfo()).create());

            }, 5);
        }
    }


    private static ItemBuilder getProfile(SkinInfo skin) {
        String PROFILE_DISPLAY_NAME = "§3§lProfil §8» §7§oEinstellungen / Stats / Freunde";
        return Skull.fromMojangValue(skin.getValue(), 1)
                .toItemBuilder()
                .displayName(PROFILE_DISPLAY_NAME);
    }


    public void playerLeaved(Player p) {
        hiddenPlayers.remove(p);
    }

}
