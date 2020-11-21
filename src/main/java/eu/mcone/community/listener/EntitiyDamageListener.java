package eu.mcone.community.listener;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.community.utils.events.Event;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntitiyDamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setCancelled(!CommunityPlugin.getInstance().getEventManager().getCurrentEvent().contains(Event.PVP));
        }
        if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL) && e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setCancelled(!CommunityPlugin.getInstance().getEventManager().getCurrentEvent().contains(Event.PVP));
        }
    }
}


