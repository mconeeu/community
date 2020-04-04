package eu.mcone.community.Listener;

import eu.mcone.community.CommunityPlugin;
import eu.mcone.lobby.api.LobbyWorld;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void on(PlayerMoveEvent e) {
        Player p = e.getPlayer();


        //entrance
        Location buehne1 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehen3");
        Location buehne2 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehen4");
        Location buehne3 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehen1");
        Location buehne4 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehen2");


        //back stairs
        Location backStairs1 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehne-s2");
        Location backStairs2 = CommunityPlugin.getInstance().getCommunityWorld().getLocation("buehne-s1");

        if (!p.hasPermission("community.buehne")) {
            //entrance left
            if (p.getLocation().distance(buehne1) <= 2 ||
                    p.getLocation().distance(buehne2) <= 2) {

                Vector v1 = p.getLocation().getDirection().multiply(0.8).setX(0.7);
                p.setVelocity(v1);
            } else if (
                //right
                    p.getLocation().distance(buehne3) <= 2 ||
                            p.getLocation().distance(buehne4) <= 2) {

                Vector v1 = p.getLocation().getDirection().multiply(0.8).setX(-0.7);
                p.setVelocity(v1);
            }
            //entrance backstairs
            if (p.getLocation().distance(backStairs1) <= 2 ||
                    p.getLocation().distance(backStairs2) <= 2) {

                Vector v1 = p.getLocation().getDirection().multiply(0.8).setZ(-0.7);
                p.setVelocity(v1);
            }


        }

        for (Player players : InventoryTriggerListener.run.keySet()) {
            if (!p.hasPermission("community.bypass")) {
                if (p.getLocation().distance(players.getLocation()) <= 4) {

                    double Ax = p.getLocation().getX();
                    double Ay = p.getLocation().getY();
                    double Az = p.getLocation().getZ();

                    double Bx = players.getLocation().getX();
                    double By = players.getLocation().getY();
                    double Bz = players.getLocation().getZ();

                    double x = Ax - Bx;
                    double y = Ay - By;
                    double z = Az - Bz;
                    Vector v = new Vector(x, y, z).normalize();
                    p.setVelocity(v);

                }
            }
        }
        if (InventoryTriggerListener.run.containsKey(p)) {
            for (Entity entity : p.getNearbyEntities(4, 4, 4)) {
                if (entity instanceof Player) {
                    Player target = (Player) entity;
                    if (p != target) {
                        if (!target.hasPermission("community.bypass")) {

                            double Ax = p.getLocation().getX();
                            double Ay = p.getLocation().getY();
                            double Az = p.getLocation().getZ();

                            double Bx = target.getLocation().getX();
                            double By = target.getLocation().getY();
                            double Bz = target.getLocation().getZ();

                            double x = Bx - Ax;
                            double y = By - Ay;
                            double z = Bz - Az;
                            Vector v = new Vector(x, y, z).normalize();
                            target.setVelocity(v);

                        }
                    }
                }
            }
        }

    }
}
