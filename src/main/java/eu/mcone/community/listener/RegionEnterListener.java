package eu.mcone.community.listener;

import eu.mcone.community.utils.CommunityRegion;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.event.world.RegionEnterEvent;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RegionEnterListener implements Listener {

    @EventHandler
    public void onRegionEnter(RegionEnterEvent e) {
        CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(e.getPlayer());

        for (CommunityRegion region : CommunityRegion.values()) {
            if (region.getRegionName().equals(e.getRegion().getName())) {
                if (!corePlayer.isNicked()) {
                    for (String permission : region.getPermissions()) {
                        if (corePlayer.hasPermission(permission)) {
                            return;
                        }
                    }
                }
                preventEnter(corePlayer.bukkit(), region.isBoost(), e);
            }
        }
    }

    private void preventEnter(Player player, boolean velocity, RegionEnterEvent event) {
        if (velocity) {
            player.getPlayer().setVelocity(player.getLocation().getDirection().normalize().setY(0).multiply(-1));
        }
        event.setCancelled(true);
        CoreSystem.getInstance().getMessenger().sendTransl(player, "community.stage.area");
    }
}
