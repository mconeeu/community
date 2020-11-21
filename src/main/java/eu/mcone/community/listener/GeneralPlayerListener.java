package eu.mcone.community.listener;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.command.CorePlayerCommand;
import eu.mcone.coresystem.api.bukkit.event.player.PlayerVanishEvent;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;

public class GeneralPlayerListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();
            if (!CommunityPlugin.getInstance().getBuildSystem().hasBuildModeEnabled(p)) e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void on(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        CommunityPlugin.getInstance().getShieldManager().push(p);
    }

    @EventHandler
    public void on(PlayerRespawnEvent e) {

        CorePlayer cp = CoreSystem.getInstance().getCorePlayer(e.getPlayer());
        CommunityPlugin.getInstance().getVanishManager().updateInventory(cp);
    }

    @EventHandler
    public void onVanish(PlayerVanishEvent e) {
        CorePlayer player = e.getPlayer();

        if (e.isVanished()) {
            if (CommunityPlugin.getInstance().getShieldManager().getRun().containsKey(player.bukkit())) {
                CommunityPlugin.getInstance().getShieldManager().getRun().get(player.bukkit()).cancel();
                CommunityPlugin.getInstance().getShieldManager().getRun().remove(player.bukkit());
                CommunityPlugin.getInstance().getMessenger().send(player.bukkit(), "§4Das Schutzschild wurde deaktivert, weil du den Vanish Modus betreten hast!");
            }
        }
    }

    @EventHandler
    public void on(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();

        e.setCancelled(p.getGameMode() != GameMode.CREATIVE);
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();

        e.setCancelled(p.getGameMode() != GameMode.CREATIVE);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();

        e.getDrops().clear();
        e.setDroppedExp(0);


        e.setDeathMessage("");
        p.spigot().respawn();
        p.teleport(CommunityPlugin.getInstance().getCommunityWorld().getLocation("spawn"));
    }

    @EventHandler
    public void onAchievementAwarded(PlayerAchievementAwardedEvent e) {
        e.setCancelled(true);
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);


    }
}
