package eu.mcone.community.utils.vanish;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.listener.PlayerVanishListener;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.core.player.Group;
import eu.mcone.gameapi.api.GameAPI;
import lombok.Getter;
import org.bukkit.Effect;
import org.bukkit.Sound;
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

            CommunityPlugin.getInstance().getMessenger().sendSuccess(p, "Du hast die Spielersichtbarkeit auf ![" + target.getName() + "] geändert!");

        }

    }

    public eu.mcone.community.utils.vanish.VanishPlayerVisibility getVanishPlayerVisibility(Player p) {
        return hiddenPlayers.getOrDefault(p, eu.mcone.community.utils.vanish.VanishPlayerVisibility.EVERYBODY);
    }


    public void playerLeaved(Player p) {
        hiddenPlayers.remove(p);
    }

}
