package eu.mcone.community.listener;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.coresystem.api.bukkit.event.nick.NickEvent;
import eu.mcone.coresystem.api.bukkit.event.nick.UnnickEvent;
import eu.mcone.coresystem.api.bukkit.event.player.LanguageChangeEvent;
import eu.mcone.coresystem.api.bukkit.event.player.PermissionChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChangeListener implements Listener {

    @EventHandler
    public void on(PermissionChangeEvent e) {
        CommunityPlugin.getInstance().getVanishManager().updateInventory(e.getPlayer());
    }

    @EventHandler
    public void on(LanguageChangeEvent e) {
        CommunityPlugin.getInstance().getVanishManager().updateInventory(e.getPlayer());
    }

    @EventHandler
    public void onNick(NickEvent e) {
        if (CommunityPlugin.getInstance().getShieldManager().getRun().containsKey(e.getPlayer().bukkit())) {
            CommunityPlugin.getInstance().getShieldManager().getRun().get(e.getPlayer().bukkit()).cancel();
            CommunityPlugin.getInstance().getShieldManager().getRun().remove(e.getPlayer().bukkit());
        }

        e.getPlayer().bukkit().teleport(CommunityPlugin.getInstance().getCommunityWorld().getLocation("spawn"));
        CommunityPlugin.getInstance().getVanishManager().updateInventory(e.getPlayer());
    }

    @EventHandler
    public void onUnNick(UnnickEvent e) {
        CommunityPlugin.getInstance().getVanishManager().updateInventory(e.getPlayer());
    }

}
